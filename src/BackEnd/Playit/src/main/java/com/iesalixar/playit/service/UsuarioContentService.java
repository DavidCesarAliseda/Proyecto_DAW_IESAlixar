package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.model.UsuarioContent;

public interface UsuarioContentService {

	public UsuarioContent findUsuarioContentByContentAndUsuario(UsuarioContent uc);
	public List<UsuarioContent> findUsuarioByContentUsuario(Usuario user);
	public List<UsuarioContent> findAllUsuarioContentByUsuarioAndStatus(Usuario usuario, String status);
	public void deleteUsuarioContent(UsuarioContent uc);
}