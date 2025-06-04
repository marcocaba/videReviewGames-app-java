package com.marcoMario.service;

import com.marcoMario.iService.IGameService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;
import com.marcoMario.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getFewGames() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Game> gamesPage = gameRepository.getAllGamesWithDetails(pageable);
        return gamesPage.getContent();
    }

    @Override
    public List<GameDTO> getFewGamesDTO() {
        System.out.println("2");
        Pageable pageable = PageRequest.of(0, 20);
        Page<GameDTO> gamesPage = gameRepository.getAllGamesWithDetailsDTO(pageable);
        System.out.println("3");
        return gamesPage.getContent();
    }
}
