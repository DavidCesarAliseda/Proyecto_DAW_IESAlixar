package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
	public Serie findByTitle(String title);
	public Serie findByTitleAndContentId(String title, Long contentId);
	public Serie findByUrlPlatform(String urlPlatform);
	public Serie findByContentIdAndUrlPlatform(Long contentId, String urlPlatform);
}
