package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Person;
import com.iesalixar.playit.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	PersonRepository personRepo;

	@Override
	public List<Person> getAllPersons() {
		
		List<Person> personDB = personRepo.findAll();

		if (personDB != null && personDB.size() > 0) {
			return personDB;
		}

		return new ArrayList<Person>();
	}

	@Override
	public Person addPerson(Person personDB) {
		Person person = personRepo.findByNameAndSurname1AndSurname2(personDB.getName(), personDB.getSurname1(), personDB.getSurname2());
		if (personDB == null || getPersonByID(personDB.getPersonId()) != null || personDB.equalPerson(person) ) {
			return null;
		}
		return personRepo.save(personDB);
	}

	@Override
	public Person getPersonByName(String name) {

		if (name != null) {
			Person person = personRepo.findByName(name);
			return person;
		}

		return null;
	}
	
	@Override
	public Person getPersonBySurname1(String surname1) {
		if (surname1 != null) {
			Person person = personRepo.findBySurname1(surname1);
			return person;
		}
		return null;
	}

	@Override
	public Person getPersonBySurname2(String surname2) {
		if (surname2 != null) {
			Person person = personRepo.findBySurname2(surname2);
			return person;
		}
		return null;
	}

	@Override
	public Person deletePerson(Long id) {
		if (id != null) {
			Person person = personRepo.findById(id).get();
			personRepo.delete(person);
			
			return person;
		}
		return null;
	}

	@Override
	public Person getPersonByID(Long id) {
		if (id != null) {
			return personRepo.findById(id).get();
		}
		return null;
	}

	@Override
	public Person editPerson(Person personDB) {
		Person person = personRepo.findByNameAndSurname1AndSurname2(personDB.getName(), personDB.getSurname1(), personDB.getSurname2());
		if(personDB == null || getPersonByID(personDB.getPersonId()) == null || personDB.equalPerson(person)) {
			return null;
		}
		return personRepo.save(personDB);
	}
}
