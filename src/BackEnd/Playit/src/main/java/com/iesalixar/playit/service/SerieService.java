package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Content;
import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Serie;

public interface SerieService {

	public List<Serie> getAllSeries();

	public Serie addSerie(Serie serie);

	public Serie getSerieByTitle(String title);

	public Serie deleteSerie(Long id);

	public Serie getSerieByID(Long id);

	public Serie editSerie(Serie Serie);

	public boolean isSerie(Content content);

	public List<Serie> getAllSeriesByGenre(Genre genre);
}
