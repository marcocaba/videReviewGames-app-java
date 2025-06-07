package com.marcoMario.iService;

import com.marcoMario.model.Creator;

import java.util.Set;

public interface ICreatorService {

    Set<Creator> getCreatorsByGameId(long idGame);

    Creator getCreatorById(long idCreator);
}
