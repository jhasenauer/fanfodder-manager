package com.fanfodder.manager.data.repository.solr;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.fanfodder.manager.data.model.solr.FanFodderDocument;

public interface FanFodderDocumentRepository extends
		SolrCrudRepository<FanFodderDocument, String>,
		PagingAndSortingRepository<FanFodderDocument, String>,
		FanFodderDocumentRepositoryCustom {

	public Page<FanFodderDocument> findByTitleContainsOrSummaryContains(String title, String summary, Pageable page);
	
	@Query("title:*?0* OR description:*?0*")
	public List<FanFodderDocument> findByQueryAnnotation(String searchTerm, Pageable page);
}
