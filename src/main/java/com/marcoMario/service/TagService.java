package com.marcoMario.service;

import com.marcoMario.iService.ITagService;
import com.marcoMario.model.Tag;
import com.marcoMario.repository.CreatorRepository;
import com.marcoMario.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService implements ITagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag getTagById(long idTag) {
        return tagRepository.findById(idTag).orElse(null);
    }

}
