package com.marcoMario.service;

import com.marcoMario.iService.IPlatformService;
import com.marcoMario.model.Platform;
import com.marcoMario.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlatformService implements IPlatformService {

    @Autowired
    private PlatformRepository platformRepository;

    @Override
    public Platform getPlatformById(long idPlatform) {
        return platformRepository.findById(idPlatform).orElse(null);
    }

    @Override
    public Set<Platform> getPlatformsByGameId(long gameId) {
        return platformRepository.findPlatformsByGameId(gameId);
    }

}
