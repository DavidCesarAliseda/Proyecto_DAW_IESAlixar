package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonContentKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "person_id")
	Long personId;

	@Column(name = "content_id")
	Long contentId;

	public PersonContentKey() {
		
	}

	public Long getPersonId() {
		return personId;
	}



	public void setPersonId(Long personId) {
		this.personId = personId;
	}



	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentId, personId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonContentKey other = (PersonContentKey) obj;
		return Objects.equals(contentId, other.contentId) && Objects.equals(personId, other.personId);
	}

	
}
