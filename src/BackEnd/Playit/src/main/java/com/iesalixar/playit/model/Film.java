package com.iesalixar.playit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "film")
public class Film extends Content implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String valoration;

	public Film() {
		super();
		
	}

	public String getValoration() {
		return valoration;
	}

	public void setValoration(String valoration) {
		this.valoration = valoration;
	}
	
	
	
	
}
