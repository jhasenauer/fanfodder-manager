package com.fanfodder.manager.data.repository.solr.impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FieldWithFacetParameters;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Repository;

import com.fanfodder.manager.common.util.StringUtils;
import com.fanfodder.manager.data.model.solr.FanFodderDocument;
import com.fanfodder.manager.data.repository.solr.FanFodderDocumentRepositoryCustom;

@Repository
public class FanFodderDocumentRepositoryImpl implements FanFodderDocumentRepositoryCustom {

	private static final Logger LOGGER = LogManager.getLogger(FanFodderDocumentRepositoryImpl.class);
	
	@Resource
	private SolrTemplate solrTemplate;
	
	
	@Override
	public long count(String query) {
		String[] words = query.split(" ");
		Criteria conditions = createSearchConditions(words);
		SimpleQuery countQuery = new SimpleQuery(conditions);	
		return solrTemplate.count(countQuery);
	}
	
	private Criteria createSearchConditions(String[] words) {
        Criteria conditions = null;

        for (String word: words) {
            if (conditions == null) {
                conditions = new Criteria("title").contains(word)
                        .or(new Criteria("summary").contains(word));
            }
            else {
                conditions = conditions.or(new Criteria("title").contains(word))
                        .or(new Criteria("summary").contains(word));
            }
        }

        return conditions;
    }

	@Override
	public FacetPage<FanFodderDocument> search(String query, String fq, Pageable page) {
		
		FieldWithFacetParameters feedtag = new FieldWithFacetParameters("feedtag");
        feedtag.setMinCount(1);
        
        FieldWithFacetParameters keyword = new FieldWithFacetParameters("keyword");
        keyword.setMinCount(1);
        
        FieldWithFacetParameters site = new FieldWithFacetParameters("site");
        site.setMinCount(1);
        
        FacetOptions facetOptions = new FacetOptions();
        facetOptions.addFacetOnField(feedtag);
        facetOptions.addFacetOnField(keyword);
        facetOptions.addFacetOnField(site);
        
        String[] words = query.split(" ");
        Criteria conditions = createSearchConditions(words);
        
		FacetQuery facetQuery = new SimpleFacetQuery(conditions);
		facetQuery.setFacetOptions(facetOptions);
		facetQuery.setPageRequest(page);
		
		if (!StringUtils.isEmptyOrNull(fq)) {
			String[] fqs = fq.split(",", 0);
			for (int i = 0; i < fqs.length; i++) {
				String[] filterQuery = fqs[i].split(":", 0);
				facetQuery.addFilterQuery(new SimpleQuery(new Criteria(filterQuery[0]).is(filterQuery[1])));
			}
		}
		
        SimpleQuery search = new SimpleQuery(conditions);
        search.setPageRequest(page);

        FacetPage<FanFodderDocument> results = solrTemplate.queryForFacetPage(facetQuery, FanFodderDocument.class);
        return results;
	}
}
