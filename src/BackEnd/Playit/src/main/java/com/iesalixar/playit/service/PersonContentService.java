package com.iesalixar.playit.service;

import com.iesalixar.playit.model.PersonContent;

public interface PersonContentService {

	public PersonContent deletePersonContent(PersonContent pc);
	public PersonContent findPersonContentByContentAndPerson(PersonContent pc);
}
