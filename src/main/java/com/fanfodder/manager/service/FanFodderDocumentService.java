package com.fanfodder.manager.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Repository;

import com.fanfodder.manager.data.model.solr.FanFodderDocument;
import com.fanfodder.manager.data.repository.solr.FanFodderDocumentRepository;

@Repository
public class FanFodderDocumentService {

	private static final Logger LOGGER = LogManager.getLogger(FanFodderDocumentService.class);
	
	protected static final String QUERY_METHOD_METHOD_NAME = "methodName";
    protected static final String QUERY_METHOD_NAMED_QUERY = "namedQuery";
    protected static final String QUERY_METHOD_QUERY_ANNOTATION = "queryAnnotation";
	
	@Autowired
	private FanFodderDocumentRepository ffdRepo;
	
	@Value("${solr.repository.query.method.type}")
    private String queryMethodType;
	
	public long countSearchResults(String query) {
		return ffdRepo.count(query);
	}
	
	public FacetPage<FanFodderDocument> search(String searchTerm, String fq, Pageable page) {
		LOGGER.debug("Searching documents with search term: " + searchTerm + " and page: " + page);
		return findDocuments(searchTerm, fq, page);
	}
	
	private FacetPage<FanFodderDocument> findDocuments(String searchTerm, String fq, Pageable page) {
		LOGGER.debug("using custom search: " + searchTerm + " : " + page);
        return ffdRepo.search(searchTerm, fq, page);
	}
}
