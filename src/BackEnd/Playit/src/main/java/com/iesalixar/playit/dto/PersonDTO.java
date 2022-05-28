package com.iesalixar.playit.dto;

import java.util.Date;

public class PersonDTO {
	private String name;
	private String surname1;
	private String surname2;
	private String birthdate;
	
	public PersonDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public String getBirthDate() {
		return birthdate;
	}

	public void setBirthDate(String birthDate) {
		birthdate = birthDate;
	}
	
	
	
	
}
