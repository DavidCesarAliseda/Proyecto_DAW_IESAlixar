package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PlatformContent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	PlatformContentKey id;
	
	@ManyToOne
	@MapsId("platformId")
	@JoinColumn(name="platform_id")
	Platform platform;
	
	@ManyToOne
	@MapsId("contentId")
	@JoinColumn(name="content_id")
	Content content;
	
	@Column(unique = false, nullable = false)
	String url;

	public PlatformContent() {
		
	}

	public PlatformContentKey getId() {
		return id;
	}

	public void setId(PlatformContentKey id) {
		this.id = id;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, id, platform, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlatformContent other = (PlatformContent) obj;
		return Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& Objects.equals(platform, other.platform) && Objects.equals(url, other.url);
	}
	
	
}
