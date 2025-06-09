package com.marcoMario.service;

import com.marcoMario.iService.IUserService;

import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
import com.marcoMario.model.User;
import com.marcoMario.repository.GameRepository;
import com.marcoMario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public boolean registerUser(String nameUser, String password) {
        boolean exist = false;
        Optional<User> existingUser = userRepository.findByName(nameUser);

        if (existingUser.isEmpty()) {
            User user = new User();
            user.setName(nameUser);
            user.setPsswd(password);
            userRepository.save(user);
            exist = true;
        }
        return exist;
    }

    @Override
    public ObjectPage viewFavoriteGamesById(long idUser, int page) {
        ObjectPage objectPage = new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);

        objectPage.setObjectListGameDTO(userRepository.findFavoritesByUserId(idUser, pageable));
        objectPage.setSizeList(userRepository.countFavoritesGamesByUserId(idUser));

        return objectPage;
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
}
