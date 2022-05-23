package com.iesalixar.playit.service;

import java.util.List;

import com.iesalixar.playit.model.Usuario;

public interface UsuarioService {

	public Usuario insertUsuario(Usuario usuario);
	public Usuario getUsuarioByEmail(String email);
	public List<Usuario> getAllUsuarios();
	public Usuario getUsuarioByUserName(String name);
	public Usuario addUsuario(Usuario usuario);
	public Usuario deleteUsuario(Long id);
	public Usuario editUsuario(Usuario usuario);
	public Usuario getUserById(Long id);
	public List<Usuario> getAllUsuariosByUserName(String userName);
}
