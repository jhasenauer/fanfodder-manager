package com.fanfodder.manager.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fanfodder.manager.data.model.mongo.Feed;
import com.fanfodder.manager.data.repository.mongo.FeedRepository;

@Repository
public class FeedService {

	private static final Logger LOGGER = LogManager.getLogger(FeedService.class);
	
	@Autowired
	private FeedRepository fRepo;
	
	public List<Feed> findAll() {
		return fRepo.findAll();
	}
	
	public Page<Feed> findAll(Pageable pageable) {
		return fRepo.findAll(pageable);
	}
	
	public Page<Feed> search(String query, Pageable pageable) {
		return fRepo.findByTitleLike(query, pageable);
	}
}
