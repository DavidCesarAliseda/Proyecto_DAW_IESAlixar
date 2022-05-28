package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Serie;

public interface SerieService {

	public List<Serie> getAllSeries();

	public Serie addSerie(Serie serie);

	public Serie getSerieByTitle(String title);

	public Serie deleteSerie(Long id);

	public Serie getSerieByID(Long id);

	public Serie editSerie(Serie Serie);
}
