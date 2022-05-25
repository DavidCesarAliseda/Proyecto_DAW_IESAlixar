package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "platform")
public class Platform implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long platformId;

	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(unique = false, nullable = true)
	private String logo;

	public Platform() {

	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long id) {
		this.platformId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(logo, name, platformId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Platform other = (Platform) obj;
		return Objects.equals(logo, other.logo) && Objects.equals(name, other.name)
				&& Objects.equals(platformId, other.platformId);
	}
	
	
	
	
}
