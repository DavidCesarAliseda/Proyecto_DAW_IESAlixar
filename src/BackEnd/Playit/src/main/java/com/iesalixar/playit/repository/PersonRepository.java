package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	public Person findByName(String name);
	public Person findBySurname1(String surname1);
	public Person findBySurname2(String surname2);
	public Person findByNameAndSurname1AndSurname2(String name, String surname1, String surname2);
	
}
