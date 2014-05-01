package com.fanfodder.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fanfodder.manager.data.model.mongo.Content;
import com.fanfodder.manager.data.repository.mongo.ContentRepository;

@Repository
public class ContentService {
 
    @Autowired
    private ContentRepository cRepo;
     
    public List<Content> findAll() {
        return cRepo.findAll();
    }
     
    public Page<Content> findAll(Pageable pageable) {
        return cRepo.findAll(pageable);
    }
}