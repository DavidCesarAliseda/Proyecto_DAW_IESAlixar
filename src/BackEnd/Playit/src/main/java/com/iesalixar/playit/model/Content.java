package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "content")
@Inheritance(strategy = InheritanceType.JOINED)
public class Content implements Serializable {
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

	@Column(nullable = false, columnDefinition = "text")
	private String synopsis;

	@Column(nullable = false)
	private String trailer;
	
	@Column(nullable = false)
	private String urlPlatform;


	@Column(nullable = false, unique = true)
	private String cover;
	
	@ManyToOne
	@JoinColumn(name="platform_id")
	Platform platform;
	
	@JoinTable(
			name = "contentGenre",
			joinColumns = @JoinColumn(name = "fk_content", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "fk_genre", nullable = false)
	)
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Genre> genres = new ArrayList<>();
	
	@OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
	Set<PersonContent> personContents;
	
	@OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
	Set<UsuarioContent> userContents;
	
	public Set<PersonContent> getPersonContents() {
		return personContents;
	}

	public void setPersonContents(Set<PersonContent> personContents) {
		this.personContents = personContents;
	}

	public Set<UsuarioContent> getUserContents() {
		return userContents;
	}

	public void setUserContents(Set<UsuarioContent> userContents) {
		this.userContents = userContents;
	}

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

	public String getUrlPlatform() {
		return urlPlatform;
	}

	public void setUrlPlatform(String urlPlatform) {
		this.urlPlatform = urlPlatform;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}


	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	

	public Set<PersonContent> getPersonContent() {
		return personContents;
	}

	public void setPersonContent(Set<PersonContent> personContent) {
		this.personContents = personContent;
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
	
	public void deleteGenre(Genre genre) {
		genres.remove(genre);
	}
	
	public void addGenre(Genre genre) {
		genres.add(genre);
	}
	
	public void addPersonContent (PersonContent personContent) {
		personContents.add(personContent);
	}
	
	public void deletePersonContent (PersonContent personContent) {
		personContents.remove(personContent);
	}

}
