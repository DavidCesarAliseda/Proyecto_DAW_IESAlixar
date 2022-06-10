package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Content;
import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Usuario;

public interface FilmService {

	public List<Film> getAllFilms();

	public Film addFilm(Film film);

	public Film getFilmByTitle(String title);

	public Film deleteFilm(Long id);

	public Film findFilmById(Long id);

	public Film editFilm(Film film);
	
	public boolean isFilm(Content content);
	
	public List<Film> getAllFilmsByGenre(Genre genre);
	
	public List<Film> getAllFilmsByUserAndStatus(Usuario user, String status);
}
