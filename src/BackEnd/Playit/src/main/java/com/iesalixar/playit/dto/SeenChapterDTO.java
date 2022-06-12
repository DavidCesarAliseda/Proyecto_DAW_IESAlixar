package com.iesalixar.playit.dto;

import com.iesalixar.playit.model.Chapter;

public class SeenChapterDTO {

	private Chapter chapter;
	private String seen;
	
	public SeenChapterDTO() {

	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public String getSeen() {
		return seen;
	}

	public void setSeen(String seen) {
		this.seen = seen;
	}

	
	
	
}
