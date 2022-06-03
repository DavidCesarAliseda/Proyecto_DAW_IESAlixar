package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioContentKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "usuario_id")
	Long usuarioId;

	@Column(name = "content_id")
	Long contentId;

	public UsuarioContentKey() {
	
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentId, usuarioId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioContentKey other = (UsuarioContentKey) obj;
		return Objects.equals(contentId, other.contentId) && Objects.equals(usuarioId, other.usuarioId);
	}
}
