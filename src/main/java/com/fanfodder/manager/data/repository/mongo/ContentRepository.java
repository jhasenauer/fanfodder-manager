package com.fanfodder.manager.data.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fanfodder.manager.data.model.mongo.Content;

public interface ContentRepository extends MongoRepository<Content, String>,
		PagingAndSortingRepository<Content, String> {

}