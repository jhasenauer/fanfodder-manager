package com.fanfodder.manager.data.model.solr;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class FanFodderDocument {

	public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_ID = "id";
    public static final String FIELD_TITLE = "title";
    
    @Id
    @Field
    private String id;
    
    @Field
    private String description;
    
    @Field
    private String summary;
    
    @Field
    private String title;
    
    @Field
    private String link;
    
    @Field(value="keyword")
    private List keywords;
    
    
    public FanFodderDocument() {
    	
    }
    
    public static Builder getBuilder(String id, String title) {
        return new Builder(id, title);
    }
    
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
    
    @Override
	public String toString() {
		return "FanFodderDocument [id=" + id + ", description=" + description
				+ ", summary=" + summary + ", title=" + title + ", link="
				+ link + ", keywords=" + keywords + "]";
	}
    
    public static class Builder {
        private FanFodderDocument build;

        public Builder(String id, String title) {
            build = new FanFodderDocument();
            build.id = id;
            build.title = title;
        }

        public Builder description(String description) {
            build.description = description;
            return this;
        }

        public FanFodderDocument build() {
            return build;
        }
    }

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List getKeywords() {
		return keywords;
	}

	public void setKeywords(List keywords) {
		this.keywords = keywords;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
