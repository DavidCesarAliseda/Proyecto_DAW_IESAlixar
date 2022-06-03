package com.iesalixar.playit.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.repository.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService{
	
	@Autowired
	FilmRepository filmRepo;
	
	@Override
	public Film addFilm(Film filmDB) {

		if (filmDB != null && getFilmByTitle(filmDB.getTitle()) == null) {
			Film film = filmRepo.save(filmDB);
			return film;
		}
		return null;
		
	}

	@Override
	public List<Film> getAllFilms() {
		List<Film> filmDB = filmRepo.findAll();

		if (filmDB != null && filmDB.size() > 0) {
			return filmDB;
		}

		return new ArrayList<Film>();
	}

	@Override
	public Film getFilmByTitle(String title) {
		if (title != null) {
			return filmRepo.findByTitle(title);
		}

		return null;
	}

	@Override
	public Film deleteFilm(Long id) {
		if (id != null) {
			Film film = filmRepo.findById(id).get();
			filmRepo.delete(film);
		
			return film;
		}
		return null;
	}

	@Override
	public Film getFilmByID(Long id) {
		if (id != null) {
			Film film = filmRepo.findById(id).get();
			return film;
		}
		return null;
	}

	@Override
	public Film editFilm(Film filmDB) {
		if(filmDB == null || getFilmByID(filmDB.getContentId()) == null || filmRepo.findByTitleAndContentId(filmDB.getTitle(), filmDB.getContentId()) == null && getFilmByTitle(filmDB.getTitle())!=null) {
			return null;
		}
		return filmRepo.save(filmDB);
	}

}
