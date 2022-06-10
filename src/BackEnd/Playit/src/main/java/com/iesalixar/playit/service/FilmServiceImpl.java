package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Content;
import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.model.UsuarioContent;
import com.iesalixar.playit.repository.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	FilmRepository filmRepo;

	@Autowired
	UsuarioContentServiceImpl ucService;

	@Override
	public Film addFilm(Film filmDB) {

		if (filmDB != null && getFilmByTitle(filmDB.getTitle()) == null
				&& filmRepo.findByUrlPlatform(filmDB.getUrlPlatform()) == null) {
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
	public Film findFilmById(Long id) {
		if (id != null) {
			Film film = filmRepo.findById(id).get();
			System.out.println(film);
			if (film.getValoration() == null) {
				return null;
			}
			return film;
		}
		return null;
	}

	@Override
	public Film editFilm(Film filmDB) {
		if (filmDB == null || findFilmById(filmDB.getContentId()) == null
				|| filmRepo.findByTitleAndContentId(filmDB.getTitle(), filmDB.getContentId()) == null
						&& getFilmByTitle(filmDB.getTitle()) != null
				|| filmRepo.findByContentIdAndUrlPlatform(filmDB.getContentId(), filmDB.getUrlPlatform()) == null
						&& filmRepo.findByUrlPlatform(filmDB.getUrlPlatform()) != null) {
			return null;
		}
		return filmRepo.save(filmDB);
	}

	@Override
	public boolean isFilm(Content content) {
		return filmRepo.existsById(content.getContentId());
	}

	@Override
	public List<Film> getAllFilmsByGenre(Genre genre) {

		List<Content> contents = genre.getContents();

		List<Film> films = new ArrayList();

		for (Content content : contents) {
			if (isFilm(content)) {
				films.add(findFilmById(content.getContentId()));
			}
		}
		return films;
	}

	@Override
	public List<Film> getAllFilmsByUserAndStatus(Usuario user, String status) {

		if (user.getId_usuario() != null && !status.isBlank() && !status.isEmpty()) {
			List<Film> films = new ArrayList();
			List<UsuarioContent> ucs = ucService.findAllUsuarioContentByUsuarioAndStatus(user, status);
			for (UsuarioContent usuarioContent : ucs) {
				if (isFilm(usuarioContent.getContent())) {
					films.add(findFilmById(usuarioContent.getContent().getContentId()));
				}
			}

			return films;
		}
		return null;
	}

}
