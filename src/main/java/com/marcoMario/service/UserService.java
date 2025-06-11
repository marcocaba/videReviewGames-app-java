package com.marcoMario.service;

import com.marcoMario.iService.IUserService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
import com.marcoMario.model.LoggedUser;
import com.marcoMario.model.User;
import com.marcoMario.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private AchievementsRepository achievementsRepository;

    @Autowired
    private ScreenshotsRepository screenshotsRepository;

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public String registerUser(String nameUser, String password, String SecondPassword) {
        User newUser = new User();
        Optional<User> existing = userRepository.findByName(nameUser);
        String register = "userRegister";

        if (existing.isPresent()) {
            register = "userNameExist";

        } else if (!password.equals(SecondPassword)) {
            register = "passwordsNotTheSame";

        }else {
            newUser.setName(nameUser);
            newUser.setPsswd(password);
            userRepository.save(newUser);
        }

        return register;
    }

    @Override
    public ObjectPage viewFavoriteGamesById(int page) {
        Long idUser = LoggedUser.userId;
        ObjectPage objectPage = null;

        if(idUser!=null){
            objectPage = new ObjectPage();
            Pageable pageable = PageRequest.of(page, 20);
            List<GameDTO> games = userRepository.findFavoritesByUserId(idUser, pageable);

            List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

            for(GameDTO game:games){
                gamesDTO.add(buildGameDTO(game));
            }

            objectPage.setObjectListGameDTO(gamesDTO);
            objectPage.setSizeList(userRepository.countFavoritesGamesByUserId(idUser));
        }

        return objectPage;
    }

    private GameDTO buildGameDTO(GameDTO game){
        game.setTags(tagRepository.findAllByGameId(game.getId()));
        game.setCreators(creatorRepository.findCreatorsByGameId(game.getId()));
        List<String> screenshots = screenshotsRepository.findFirstUrlByGameId(game.getId());
        if(screenshots.isEmpty()){
            game.setImage("joker");
        }else{
            game.setImageSecond(screenshots.getFirst());
        }

        return game;
    }

    @Transactional
    public String addGameToFavorites(Long gameId) {
        Long userId = LoggedUser.userId;
        String added = "needLogIn";

        if(userId!=null){
            Optional<User> optionalUser = userRepository.findById(userId);
            Optional<Game> optionalGame = gameRepository.findById(gameId);
            added = "gameAdded";

            if (optionalUser.isEmpty() || optionalGame.isEmpty()) {
                added = "empty";
            }else {
                User user = optionalUser.get();
                Game game = optionalGame.get();

                if (user.getFavorites().contains(game)) {
                    added = "contains";
                } else {
                    user.getFavorites().add(game);
                    userRepository.save(user);
                }
            }

        }

        return added;
    }

    @Transactional
    public String removeGameFromFavorites( Long gameId) {
        Long userId = LoggedUser.userId;
        String removed = "needLogIn";

        if(userId!=null){
            removed = "gameRemoved";
            Optional<User> optionalUser = userRepository.findById(userId);
            Optional<Game> optionalGame = gameRepository.findById(gameId);

            if (optionalUser.isEmpty() || optionalGame.isEmpty()) {
                removed = "empty";
            }else {
                User user = optionalUser.get();
                Game game = optionalGame.get();

                if (!user.getFavorites().contains(game)) {
                    removed = "notFound";
                } else {
                    user.getFavorites().remove(game);
                    userRepository.save(user);
                }
            }
        }

        return removed;
    }

    @Override
    public String logInUser(String nameUser, String password) {
        Optional<User> user = userRepository.findByNameAndPsswd(nameUser, password);
        String loged = "invalidCredentials";

        if (user.isPresent()) {
            loged = "loged";
            LoggedUser.userId = user.get().getIdUser();
        }

        return loged;
    }

    @Override
    public long getIdUserByName(String nameUser) {
        Optional<User> user = userRepository.findByName(nameUser);
        long id = 0;
        if(user.isPresent()){
            id = user.get().getIdUser();
        }

        return id;
    }

    @Override
    public boolean logOutUser() {
        LoggedUser.userId = null;
        return false;
    }

    @Override
    public boolean checkLogInState() {
        boolean logged = false;
        Long userId = LoggedUser.userId;

        if(userId != null){
            logged = true;
        }

        return logged;
    }

    @Override
    public String getNameUserByIdUser() {
        Long idUser = LoggedUser.userId;
        Optional<User> user = userRepository.findByIdUser(idUser);

        return user.get().getName();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findByIdUser(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }
}
