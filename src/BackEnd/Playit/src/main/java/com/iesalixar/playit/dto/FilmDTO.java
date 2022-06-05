package com.iesalixar.playit.dto;


public class FilmDTO {

	private String title;
	private String premiere;
	private String duration;
	private String country;
	private String synopsis;
	private String trailer;
	private Long valoration;
	private String urlPlatform;
	private Long platformId;
	
	public FilmDTO() {
		
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


	public Long getValoration() {
		return valoration;
	}

	public void setValoration(Long valoration) {
		this.valoration = valoration;
	}

	public String getUrlPlatform() {
		return urlPlatform;
	}

	public void setUrlPlatform(String urlPlatform) {
		this.urlPlatform = urlPlatform;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	
	
}
