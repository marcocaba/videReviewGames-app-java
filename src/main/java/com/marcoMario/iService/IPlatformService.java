package com.marcoMario.iService;

import com.marcoMario.model.Platform;

import java.util.Set;

public interface IPlatformService {

    Platform getPlatformById(long idPlatform);

    Set<Platform> getPlatformsByGameId(long gameId);
}
