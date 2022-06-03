package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlatformContentKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "platform_id")
	Long platformId;

	@Column(name = "content_id")
	Long contentId;
	
	public PlatformContentKey() {
		
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentId, platformId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlatformContentKey other = (PlatformContentKey) obj;
		return Objects.equals(contentId, other.contentId) && Objects.equals(platformId, other.platformId);
	}
	
	
}
