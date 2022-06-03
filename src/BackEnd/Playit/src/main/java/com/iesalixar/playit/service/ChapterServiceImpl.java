package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Chapter;
import com.iesalixar.playit.model.Serie;
import com.iesalixar.playit.repository.ChapterRepository;
import com.iesalixar.playit.repository.SerieRepository;

@Service
public class ChapterServiceImpl implements ChapterService{

	@Autowired
	ChapterRepository chapterRepo;
	
	
	
	@Override
	public List<Chapter> getAllChapters() {

		List<Chapter> chapterDB = chapterRepo.findAll();

		if (chapterDB != null && chapterDB.size() > 0) {
			return chapterDB;
		}

		return new ArrayList<Chapter>();
	}

	@Override
	public Chapter addChapter(Chapter chapterDB) {
		if (chapterDB == null || chapterRepo.findByNumberAndSeasonAndSerie(chapterDB.getNumber(), chapterDB.getSeason(), chapterDB.getSerie()) != null || chapterDB.getSeason() > chapterDB.getSerie().getSeasons()) {
			
			return null;
		}
		return chapterRepo.save(chapterDB);
	}

	@Override
	public Chapter getChapterByName(String name) {
		if (name != null) {
			Chapter chapter = chapterRepo.findByName(name);
			return chapter;
		}

		return null;
	}

	@Override
	public Chapter deleteChapter(Long id) {
		if (id != null) {
			Chapter chapter = chapterRepo.findById(id).get();
			chapterRepo.delete(chapter);
			
			return chapter;
		}
		return null;
	}

	@Override
	public Chapter getChapterByID(Long id) {
		if (id != null) {
			Chapter chapter = chapterRepo.findById(id).get();
			return chapter;
		}
		return null;
	}

	@Override
	public Chapter editChapter(Chapter chapterDB) {
		if(chapterDB == null || chapterRepo.findByNumberAndSeasonAndSerie(chapterDB.getNumber(), chapterDB.getSeason(), chapterDB.getSerie()) != null || chapterDB.getSeason() > chapterDB.getSerie().getSeasons()) {
			return null;
		}
		return chapterRepo.save(chapterDB);
	}

}
