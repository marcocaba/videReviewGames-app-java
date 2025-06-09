package com.marcoMario.service;

import com.marcoMario.iService.IUserService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
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
    public ObjectPage viewFavoriteGamesById(long idUser, int page) {
        ObjectPage objectPage = new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);
        List<GameDTO> games = userRepository.findFavoritesByUserId(idUser, pageable);

        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }

        objectPage.setObjectListGameDTO(gamesDTO);
        objectPage.setSizeList(userRepository.countFavoritesGamesByUserId(idUser));

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
    public String addGameToFavorites(Long userId, Long gameId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        String added = "gameAdded";

        if (optionalUser.isEmpty() || optionalGame.isEmpty()) {
            added = "empty";
        }

        User user = optionalUser.get();
        Game game = optionalGame.get();

        if (user.getFavorites().contains(game)) {
            added = "contains";
        } else {
            user.getFavorites().add(game);
            userRepository.save(user);
        }

        return added;
    }

    @Transactional
    public String removeGameFromFavorites(Long userId, Long gameId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        String removed = "gameRemoved";

        if (optionalUser.isEmpty() || optionalGame.isEmpty()) {
            removed = "empty";
            return removed;
        }

        User user = optionalUser.get();
        Game game = optionalGame.get();

        if (!user.getFavorites().contains(game)) {
            removed = "notFound";
        } else {
            user.getFavorites().remove(game);
            userRepository.save(user);
        }

        return removed;
    }

    @Override
    public String logInUser(String nameUser, String password) {
        Optional<User> user = userRepository.findByNameAndPsswd(nameUser, password);
        String loged = "loged";

        if (user.isEmpty()) {
            loged = "invalidCredentials";
        }

        return loged;
    }
}
