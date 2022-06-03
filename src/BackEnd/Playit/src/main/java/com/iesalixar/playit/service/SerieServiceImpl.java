package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Serie;
import com.iesalixar.playit.repository.SerieRepository;

@Service
public class SerieServiceImpl implements SerieService{

	@Autowired
	SerieRepository serieRepo;
	
	@Override
	public List<Serie> getAllSeries() {
		List<Serie> SerieDB = serieRepo.findAll();

		if (SerieDB != null && SerieDB.size() > 0) {
			return SerieDB;
		}

		return new ArrayList<Serie>();
	}

	@Override
	public Serie addSerie(Serie serieDB) {
		if (serieDB != null && getSerieByTitle(serieDB.getTitle()) == null) {
			Serie Serie = serieRepo.save(serieDB);
			return Serie;
		}
		return null;
		
	}

	@Override
	public Serie getSerieByTitle(String title) {
		if (title != null) {
			return serieRepo.findByTitle(title);
		}

		return null;
	}

	@Override
	public Serie deleteSerie(Long id) {
		if (id != null) {
			Serie serie = serieRepo.findById(id).get();
			serieRepo.delete(serie);
	
			return serie;
		}
		return null;
	}

	@Override
	public Serie getSerieByID(Long id) {
		if (id != null) {
			Serie Serie = serieRepo.findById(id).get();
			return Serie;
		}
		return null;
	}

	@Override
	public Serie editSerie(Serie serieDB) {
		if(serieDB == null || getSerieByID(serieDB.getContentId()) == null || serieRepo.findByTitleAndContentId(serieDB.getTitle(), serieDB.getContentId()) == null && getSerieByTitle(serieDB.getTitle())!=null) {
			return null;
		}
		return serieRepo.save(serieDB);
	}

}
