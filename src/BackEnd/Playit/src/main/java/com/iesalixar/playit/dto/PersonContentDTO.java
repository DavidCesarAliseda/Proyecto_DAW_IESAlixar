package com.iesalixar.playit.dto;


public class PersonContentDTO {
	
	Long personId;
	
	String role;

	public PersonContentDTO() {
	
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
