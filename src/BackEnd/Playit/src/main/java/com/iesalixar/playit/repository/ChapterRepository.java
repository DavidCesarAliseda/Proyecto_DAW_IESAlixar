package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Chapter;
import com.iesalixar.playit.model.Serie;


@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long>{
	public Chapter findByName(String name);
	public Chapter findByNumberAndSeasonAndSerie(Long number, Long season, Serie serie);
}
