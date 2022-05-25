package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Film;

public interface FilmService {
	
	public List<Film> getAllFilms();
	public Film addFilm(Film film);
	public Film getFilmByTitle(String title);
	public Film deleteFilm(Long id);
	public Film getFilmByID(Long id);
	public Film editFilm(Film film);
}

