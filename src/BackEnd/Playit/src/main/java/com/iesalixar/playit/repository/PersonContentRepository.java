package com.iesalixar.playit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesalixar.playit.model.Content;
import com.iesalixar.playit.model.Person;
import com.iesalixar.playit.model.PersonContent;
import com.iesalixar.playit.model.PersonContentKey;

public interface PersonContentRepository extends JpaRepository<PersonContent, Long>{
	public List<PersonContent> findByContent(Content content);
	public PersonContent findByContentAndPerson(Content content, Person person);
}
