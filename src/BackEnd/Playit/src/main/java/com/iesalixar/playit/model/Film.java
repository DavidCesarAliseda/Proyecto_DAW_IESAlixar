package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "film")
public class Film extends Content implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private Long valoration;

	public Film() {
		
	}

	public Long getValoration() {
		return valoration;
	}

	public void setValoration(Long valoration) {
		this.valoration = valoration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(valoration);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(valoration, other.valoration);
	}
}