package com.fanfodder.manager.web.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fanfodder.manager.common.util.PaginationInfo;
import com.fanfodder.manager.data.model.mongo.Feed;
import com.fanfodder.manager.service.FeedService;
 /**
  * Controller used for requests to access and edit feeds in MongoDB
  * 
  * @author Jeff
  *
  */
@Controller
@RequestMapping("/feed")
public class FeedController {

	private static final Logger LOGGER = LogManager.getLogger(FeedController.class);
	
	@Autowired
	FeedService feedService;

	
	@RequestMapping
	public String search(@RequestParam(required = true) String query,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize,
			ModelMap model) {
		LOGGER.debug("Search feeds collection with search term: " + query);
		
		String listAction = "feed";
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 25;
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        
        // create Pageable and search for feeds
        Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
        Page<Feed> feeds = feedService.search(query, pageable);
        
        // get the query params
        Map<String, String> listActionParams = new HashMap<String, String>();
        listActionParams.put("query", query);
        
        PaginationInfo pageInfo = new PaginationInfo(currentPage, feeds.getTotalElements(), pageLimit, listAction, listActionParams);
		
        model.addAttribute("query", query);
        model.addAttribute("totalResults", feeds.getContent().size());
        model.addAttribute("feeds", feeds.getContent());
        model.addAttribute("pageInfo", pageInfo);
        
        LOGGER.debug("Found " + feeds.getContent().size() + " feeds.");
        
		return listAction;
	}
}
