package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesalixar.playit.model.Person;



public interface PersonRepository extends JpaRepository<Person, Long>{
	public Person findByName(String name);
	public Person findBySurname1(String surname1);
	public Person findBySurname2(String surname2);
}
