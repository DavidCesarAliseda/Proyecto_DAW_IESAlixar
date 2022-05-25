package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

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
	public Person addPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person deletePerson(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person editPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
