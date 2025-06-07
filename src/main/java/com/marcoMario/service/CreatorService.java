package com.marcoMario.service;

import com.marcoMario.iService.ICreatorService;
import com.marcoMario.model.Creator;
import com.marcoMario.repository.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreatorService implements ICreatorService {

    @Autowired
    private CreatorRepository creatorRepository;

    @Override
    public Set<Creator> getCreatorsByGameId(long idGame) {
        return creatorRepository.findCreatorsByGameId(idGame);
    }

    @Override
    public Creator getCreatorById(long idCreator) {
        return creatorRepository.findById(idCreator).orElse(null);
    }
}
