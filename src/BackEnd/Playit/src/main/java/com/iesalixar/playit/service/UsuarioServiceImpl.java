package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository userRepo;

	@Override
	public Usuario insertUsuario(Usuario usuario) {

		if (usuario != null && usuario.getId_usuario() == null) {

			return userRepo.save(usuario);
		}

		return null;

	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		if (email != null) {
			return userRepo.findByEmail(email);
		}
		return null;
	}

	@Override
	public Usuario getUsuarioByUserName(String userName) {
		if (!userName.equals("") && userName != null) {
			Usuario user = userRepo.findByUserName(userName);
			return user;
		}
		return null;
	}

	@Override
	public Usuario addUsuario(Usuario usuario) {

		if (usuario != null && getUsuarioByUserName(usuario.getUserName()) == null
				&& getUsuarioByEmail(usuario.getEmail()) == null) {
			Usuario user = userRepo.save(usuario);
			return user;
		}

		return null;
	}

	@Override
	public Usuario deleteUsuario(Long id) {
		if (id != null) {
			Usuario user = userRepo.getById(id);
			userRepo.delete(user);

			return user;
		}
		return null;
	}

	@Override
	public Usuario editUsuario(Usuario usuarioDB) {
		if (usuarioDB == null || getUserById(usuarioDB.getId_usuario()) == null || getUsuarioByUserName(usuarioDB.getUserName()) == null || getAllUsuariosByUserName(usuarioDB.getUserName()).size() > 0 ) {
			return null;

		}
		return userRepo.save(usuarioDB);
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> usersDB = userRepo.findAll();

		if (usersDB != null && usersDB.size() > 0) {
			return usersDB;
		}
		return new ArrayList<Usuario>();
	}

	@Override
	public Usuario getUserById(Long id) {

		if (id != null) {
			Usuario user = userRepo.getById(id);
			return user;
		}
		return null;
	}

	@Override
	public List<Usuario> getAllUsuariosByUserName(String userName) {
		List<Usuario> usersDB = userRepo.findAllByUserName(userName);
		if (usersDB != null && usersDB.size() > 0) {
			return usersDB;
		}
		return new ArrayList<Usuario>();
	}

}
