package com.iesalixar.playit.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository userRepo;
	
	@Override
	public Usuario insertUsuario(Usuario usuario) {
		
		if (usuario!=null && usuario.getId_usuario()==null) {
			
			return userRepo.save(usuario);
		}
		
		return null;
		
	}

	@Override
	public Usuario findUsuarioByEmail(String email) {
		if(!email.equals("") && email!=null) {
			return userRepo.findByEmail(email);
		}
		return null;
	}

}
