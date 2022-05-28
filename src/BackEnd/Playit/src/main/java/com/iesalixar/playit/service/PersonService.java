package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Person;

public interface PersonService {

	public List<Person> getAllPersons();
	public Person addPerson(Person person);
	public Person getPersonByName(String name);
	public Person getPersonBySurname1(String surname1);
	public Person getPersonBySurname2(String surname2);
	public Person deletePerson(Long id);
	public Person getPersonByID(Long id);
	public Person editPerson(Person person);
}
