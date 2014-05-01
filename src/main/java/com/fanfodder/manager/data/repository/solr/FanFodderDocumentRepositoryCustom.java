package com.fanfodder.manager.data.repository.solr;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;

import com.fanfodder.manager.data.model.solr.FanFodderDocument;

public interface FanFodderDocumentRepositoryCustom {
	
	public long count(String query);
	
	public FacetPage<FanFodderDocument> search(String query, String fq, Pageable page);
}
