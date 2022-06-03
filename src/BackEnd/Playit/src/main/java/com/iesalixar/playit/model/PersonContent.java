package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PersonContent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	PersonContentKey id;
	
	@ManyToOne
	@MapsId("personId")
	@JoinColumn(name="person_id")
	Person person;
	
	@ManyToOne
	@MapsId("contentId")
	@JoinColumn(name="content_id")
	Content content;
	
	@Column(unique = false, nullable = false)
	String role;

	public PersonContent() {
		
	}

	public PersonContentKey getId() {
		return id;
	}

	public void setId(PersonContentKey id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, id, person, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonContent other = (PersonContent) obj;
		return Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& Objects.equals(person, other.person) && Objects.equals(role, other.role);
	}
	
}
