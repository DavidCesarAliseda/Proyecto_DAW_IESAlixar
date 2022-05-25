package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesalixar.playit.model.Person;



public interface PersonRepository extends JpaRepository<Person, Long>{
	public Person findByName(String name);
}
