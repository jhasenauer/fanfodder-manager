package com.fanfodder.manager.data.repository.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fanfodder.manager.data.model.mongo.Feed;

public interface FeedRepository extends MongoRepository<Feed, String>,
		PagingAndSortingRepository<Feed, String> {

	Page<Feed> findByTitleLike(String title, Pageable page);
}
