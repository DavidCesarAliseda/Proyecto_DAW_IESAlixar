package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService{
	@Autowired
	GenreRepository genreRepo;
	
	@Override
	public List<Genre> getAllGenres() {

		List<Genre> genreDB = genreRepo.findAll();

		if(genreDB!=null && genreDB.size()>0) {
			return genreDB;
		}

		return new ArrayList<Genre>();
	}
	
	@Override
	public Genre getGenreByName(String name) {

		if (name!=null) {
			Genre genre = genreRepo.findByName(name);
			return genre;
		}
		
		return null;
	}

	@Override
	public Genre addGenre(Genre genreDB) {

			if(genreDB!=null && getGenreByName(genreDB.getName())==null) {
				Genre genre = genreRepo.save(genreDB);
				return genre;
			}
		return null;
	}

	@Override
	public Genre deleteGenre(Long id) {
		
		if(id!=null) {
			Genre genre = genreRepo.getById(id);
			genreRepo.delete(genre);;
			return genre;
		}
		return null;
	}

	@Override
	public Genre getGenreByID(Long id) {
		
		if(id != null) {
			Genre genre = genreRepo.getById(id);
			return genre;
		}
		return null;
	}

	@Override
	public Genre editGenre(Genre genreDB) {
		if(genreDB!=null && getGenreByName(genreDB.getName())!=null) {
			Genre genre = genreRepo.save(genreDB);
			return genre;
		}
	return null;
	}
}
