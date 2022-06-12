package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname1;

	@Column(nullable = false)
	private String surname2;

	@Column(nullable = false, columnDefinition="varchar(10)")
	private String BirthDate;
	
	@OneToMany(mappedBy = "person")
	Set<PersonContent> personContents;

	public Person() {

	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long id) {
		this.personId = id;
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
		return BirthDate;
	}

	public void setBirthDate(String birthdate) {
		BirthDate = birthdate;
	}

	public Set<PersonContent> getPersonContent() {
		return personContents;
	}

	public void setPersonContent(Set<PersonContent> personContent) {
		this.personContents = personContent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(BirthDate, name, personId, surname1, surname2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(BirthDate, other.BirthDate) && Objects.equals(name, other.name)
				&& Objects.equals(personId, other.personId) && Objects.equals(surname1, other.surname1)
				&& Objects.equals(surname2, other.surname2);
	}
	
	public boolean equalPerson(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname1, other.surname1)
				&& Objects.equals(surname2, other.surname2);
	}
	
	public void addPersonContent (PersonContent personContent) {
		personContents.add(personContent);
	}
	
	public void deletePersonContent (PersonContent personContent) {
		personContents.remove(personContent);
	}


}
