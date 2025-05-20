package com.marcoMario.service;

import com.marcoMario.iService.IGameService;
import com.marcoMario.model.Game;
import com.marcoMario.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.getAllGamesWithDetails();
    }
}
