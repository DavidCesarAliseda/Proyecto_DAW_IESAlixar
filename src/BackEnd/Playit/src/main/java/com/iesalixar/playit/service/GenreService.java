package com.iesalixar.playit.service;
import java.util.List;

import com.iesalixar.playit.model.Genre;

public interface GenreService {

		public List<Genre> getAllGenres();
		public Genre addGenre(Genre genre);
		public Genre getGenreByName(String name);
		public Genre deleteGenre(Long id);
		public Genre getGenreByID(Long id);
		public Genre editGenre(Genre genre);
}
