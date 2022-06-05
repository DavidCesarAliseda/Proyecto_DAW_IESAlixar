package com.iesalixar.playit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.PersonContent;
import com.iesalixar.playit.repository.PersonContentRepository;

@Service
public class PersonContentServiceImpl implements PersonContentService {

	@Autowired
	PersonContentRepository pcRepo;

	@Override
	public PersonContent deletePersonContent(PersonContent pc) {

		if (pc != null) {
			pcRepo.delete(pc);
			return pc;
		}

		return null;
	}

	@Override
	public PersonContent findPersonContentByContentAndPerson(PersonContent pc) {
		if(pc.getContent() != null && pc.getPerson() != null) {
			return pcRepo.findByContentAndPerson(pc.getContent(), pc.getPerson());
		}
		return null;
	}

}
