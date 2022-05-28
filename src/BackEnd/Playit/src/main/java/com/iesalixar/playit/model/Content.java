package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "content")
@Inheritance(strategy = InheritanceType.JOINED)
public class Content implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contentId;

	@Column(nullable = false, unique = true)
	private String title;

	@Column(nullable = false)
	private String premiere;

	@Column(nullable = false)
	private String duration;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false, columnDefinition="text")
	private String synopsis;

	@Column(nullable = false)
	private String trailer;

	@Column(nullable = false)
	private String cover;

	public Content() {

	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPremiere() {
		return premiere;
	}

	public void setPremiere(String premiere) {
		this.premiere = premiere;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentId, country, cover, duration, premiere, synopsis, title, trailer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		return Objects.equals(contentId, other.contentId) && Objects.equals(country, other.country)
				&& Objects.equals(cover, other.cover) && Objects.equals(duration, other.duration)
				&& Objects.equals(premiere, other.premiere) && Objects.equals(synopsis, other.synopsis)
				&& Objects.equals(title, other.title) && Objects.equals(trailer, other.trailer);
	}

	
}
