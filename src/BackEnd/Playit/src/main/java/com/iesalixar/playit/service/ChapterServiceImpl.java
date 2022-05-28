package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Chapter;
import com.iesalixar.playit.repository.ChapterRepository;

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
	public Chapter addChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chapter getChapterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chapter deleteChapter(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chapter getChapterByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chapter editChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		return null;
	}

}
