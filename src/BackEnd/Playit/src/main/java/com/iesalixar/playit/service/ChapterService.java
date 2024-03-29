package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Chapter;
import com.iesalixar.playit.model.Serie;

public interface ChapterService {
	
	public List<Chapter> getAllChapters();

	public Chapter addChapter(Chapter chapter);

	public Chapter getChapterByName(String name);

	public Chapter deleteChapter(Long id);

	public Chapter getChapterByID(Long id);

	public Chapter editChapter(Chapter chapter);
	
	public void deleteChapters(Serie serie);
	
}
