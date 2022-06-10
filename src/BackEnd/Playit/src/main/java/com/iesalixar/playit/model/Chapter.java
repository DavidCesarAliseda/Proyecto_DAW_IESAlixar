package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "chapter")
public class Chapter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chapterId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Long number;
	
	@Column(nullable = false)
	private Long season;

	@ManyToOne
	@JoinColumn(name="serie_id")
	Serie serie;
	
	@ManyToMany(mappedBy = "chapters")
	private List<Usuario> users = new ArrayList<>();
	
	public Chapter() {
		
	}

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getSeason() {
		return season;
	}

	public void setSeason(Long season) {
		this.season = season;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chapterId, name, number, season, serie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chapter other = (Chapter) obj;
		return Objects.equals(chapterId, other.chapterId) && Objects.equals(name, other.name)
				&& Objects.equals(number, other.number) && Objects.equals(season, other.season)
				&& Objects.equals(serie, other.serie);
	}
	
	
}
