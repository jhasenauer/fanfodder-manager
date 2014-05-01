package com.fanfodder.manager.data.model.mongo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feeds")
public class Feed {

	@Id
	private String id;
	@Indexed(unique = true)
	private String url;
	private String title;
	private String description;
	private String feedType;
	private String copyright;
	private String link;
	private Date pubDate;
	private Date dateAdded;
	private Date lastProcessedDate;
	private boolean status = true;
	private List tags;
	
	@Transient
	private List<Content> entries;

	public Feed() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public Date getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getLastProcessedDate() {
		return lastProcessedDate;
	}

	public void setLastProcessedDate(Date lastProcessedDate) {
		this.lastProcessedDate = lastProcessedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Feed [id=" + id + ", url=" + url + ", title=" + title
				+ ", description=" + description + ", feedType=" + feedType
				+ ", copyright=" + copyright + ", link=" + link + ", pubDate="
				+ pubDate + ", dateAdded=" + dateAdded + ", lastProcessedDate="
				+ lastProcessedDate + ", status=" + status + ", tags=" + tags
				+ ", entries=" + entries + "]";
	}

	public List<Content> getEntries() {
		return entries;
	}

	public void setEntries(List<Content> entries) {
		this.entries = entries;
	}

	public List getTags() {
		return tags;
	}

	public void setTags(List tags) {
		this.tags = tags;
	}
}
