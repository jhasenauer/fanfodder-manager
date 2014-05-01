package com.fanfodder.manager.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fanfodder.manager.common.util.PaginationInfo;
import com.fanfodder.manager.common.util.StringUtils;
import com.fanfodder.manager.data.model.mongo.Content;
import com.fanfodder.manager.data.model.solr.FanFodderDocument;
import com.fanfodder.manager.service.ContentService;
import com.fanfodder.manager.service.FanFodderDocumentService;

/**
 * Controller used for requests to access and edit content in MongoDB and Solr
 * 
 * 
 * @author icjhasen
 *
 */
@Controller
@RequestMapping(value = "/content")
public class ContentController {
  
	private static final Logger LOGGER = LogManager
			.getLogger(ContentController.class);
	
    @Autowired
    ContentService contentService;
    
    @Autowired
    FanFodderDocumentService ffdService;
    
    @Value("${solr.server.url}")
    private String solrServerUrl;
    
    /**
     * Find all content in MongoDB
     * 
     * @param page
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/mongo", method = RequestMethod.GET)
    public String getContent(@RequestParam(required = false) String page,
            @RequestParam(required = false) String pageSize, ModelMap model) {
          
        int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 25;
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        String listAction = "content";
          
        Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
        Page<Content> content = contentService.findAll(pageable);
        PaginationInfo pageInfo = new PaginationInfo(currentPage,
                content.getTotalElements(), pageLimit, listAction);
          
        model.addAttribute("content", content.getContent());
        model.addAttribute("pageInfo", pageInfo);
          
        return listAction;
    }
    
    /**
     * Search for content in Solr
     * 
     * @param query
     * @param page
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping
	public String search(@RequestParam(required = true) String query,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, 
			@RequestParam(required = false) String fq, ModelMap model) {
		LOGGER.debug("Search fanfodder entries with search term: " + query);
		
		String listAction = "content";
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 25;
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        
        // ugly code to format and encode the filter queries
        // the encoding is done for the RSS link
        String fqParam = "";
        if (!StringUtils.isEmptyOrNull(fq)) {
        	String[] fqs = fq.split(",", 0);
        	for (int i = 0; i < fqs.length; i++) {
        		try {
        			model.addAttribute("fq", fqs[i].split("&fq=", 0)[0]);
        			String[] keyValue = fqs[i].split(":", 0);
        			String key = keyValue[0];
        			String value = "\"" + URLEncoder.encode(keyValue[1], "UTF-8") + "\"";
        			fqs[i] = key + URLEncoder.encode(":", "UTF-8") + value;
        			fqParam += "&fq=" + fqs[i];
				} catch (UnsupportedEncodingException e) {
					LOGGER.error(e);
				}
        	}
        }
        
        // create Pageable and search for documents
        Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
        FacetPage<FanFodderDocument> content = ffdService.search(query, fq, pageable);
      
        // get all facets and put into model
        Collection<Page<? extends FacetEntry>> facets = content.getAllFacets();
        Iterator iter = facets.iterator();
        while (iter.hasNext()) {
        	Page p = (Page) iter.next();
        	List pList = p.getContent();
        	if (pList.size() > 0) {
	        	SimpleFacetFieldEntry entry = (SimpleFacetFieldEntry)pList.get(0);
	        	model.addAttribute(entry.getField().toString(), p.getContent());
        	}
        }
        
        // get the query and filter query params
        Map<String, String> listActionParams = new HashMap<String, String>();
        listActionParams.put("query", query);
        if (!StringUtils.isEmptyOrNull(fq)) {
        	listActionParams.put("fq", fq);
        }
        
        PaginationInfo pageInfo = new PaginationInfo(currentPage,
                content.getTotalElements(), pageLimit, listAction, listActionParams);

        // create RSS query and URL
        String rssQuery = query;
        if (StringUtils.isEmptyOrNull(rssQuery)) {
        	try {
				rssQuery = URLEncoder.encode("*:*", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				LOGGER.error(e);
			}
        }
        String rssUrl = solrServerUrl + "/select?q=" + rssQuery + fqParam + "&wt=xslt&tr=rss.xsl";
         
        
        model.addAttribute("query", query);
        model.addAttribute("rssUrl", rssUrl);
        model.addAttribute("totalResults", content.getContent().size());
        model.addAttribute("content", content.getContent());
        model.addAttribute("pageInfo", pageInfo);
        
		LOGGER.debug("Found " + content.getContent().size() + " fanfodder entries");
		
		return listAction;
	}
} 