package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
	public Genre findByName(String name);
}
