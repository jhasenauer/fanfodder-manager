package com.fanfodder.manager.data.model.mongo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "content")
public class Content {

	@Id
    private String id;
    @DBRef
    private Feed feed;
      
    @Indexed(unique = true)
    private String url;
    private String title;
    private String description;
    private String author;
    private Date pubDate;
    private Date updatedDate;
          
    private List enclosures;
    private List tags;
    private List mediaContent;
    private List thumbnails;
    
    private String encodedContent;
    private String encodedType;
    
    
    public Content() {
          
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List getEnclosures() {
		return enclosures;
	}

	public void setEnclosures(List enclosures) {
		this.enclosures = enclosures;
	}

	public List getTags() {
		return tags;
	}

	public void setTags(List tags) {
		this.tags = tags;
	}

	public List getMediaContent() {
		return mediaContent;
	}

	public void setMediaContent(List mediaContent) {
		this.mediaContent = mediaContent;
	}

	public List getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(List thumbnails) {
		this.thumbnails = thumbnails;
	}

	public String getEncodedContent() {
		return encodedContent;
	}

	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}

	public String getEncodedType() {
		return encodedType;
	}

	public void setEncodedType(String encodedType) {
		this.encodedType = encodedType;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", feed=" + feed + ", url=" + url
				+ ", title=" + title + ", description=" + description
				+ ", author=" + author + ", pubDate=" + pubDate
				+ ", updatedDate=" + updatedDate + ", enclosures=" + enclosures
				+ ", tags=" + tags + ", mediaContent=" + mediaContent
				+ ", thumbnails=" + thumbnails + ", encodedContent="
				+ encodedContent + ", encodedType=" + encodedType + "]";
	}

	
   
	
}
