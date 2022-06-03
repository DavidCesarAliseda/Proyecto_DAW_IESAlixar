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
public class UsuarioContent implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	UsuarioContentKey id;
	
	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name="usuario_id")
	Usuario usuario;
	
	@ManyToOne
	@MapsId("contentId")
	@JoinColumn(name="content_id")
	Content content;
	
	@Column(unique = false, nullable = false)
	String status;

	public UsuarioContent() {
		
	}

	public UsuarioContentKey getId() {
		return id;
	}

	public void setId(UsuarioContentKey id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, id, status, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioContent other = (UsuarioContent) obj;
		return Objects.equals(content, other.content) && Objects.equals(id, other.id)
				&& Objects.equals(status, other.status) && Objects.equals(usuario, other.usuario);
	}
	
}
