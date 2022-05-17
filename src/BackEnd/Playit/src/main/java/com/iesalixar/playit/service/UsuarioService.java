package com.iesalixar.playit.service;

import com.iesalixar.playit.model.Usuario;

public interface UsuarioService {

	public Usuario insertUsuario(Usuario usuario);
	public Usuario findUsuarioByEmail(String email);
}
