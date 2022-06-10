package com.iesalixar.playit.dto;

import com.iesalixar.playit.model.Chapter;

public class SeenChapterDTO {

	private Chapter chapter;
	private boolean seen;
	
	public SeenChapterDTO() {
		this.seen = false;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	
}
