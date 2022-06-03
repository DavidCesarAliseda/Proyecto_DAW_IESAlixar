package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>{
	public Film findByTitle(String title);
	public Film findByTitleAndContentId(String title, Long contentId);
}
