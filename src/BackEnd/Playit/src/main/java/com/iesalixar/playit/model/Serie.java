package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "serie")
public class Serie extends Content implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private Long seasons;
	
	@OneToMany(mappedBy="serie",cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Chapter> chapters = new HashSet<>();

	public Serie() {
		
	}

	public Long getSeasons() {
		return seasons;
	}

	public void setSeasons(Long seasons) {
		this.seasons = seasons;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(seasons);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		return Objects.equals(seasons, other.seasons);
	}
}