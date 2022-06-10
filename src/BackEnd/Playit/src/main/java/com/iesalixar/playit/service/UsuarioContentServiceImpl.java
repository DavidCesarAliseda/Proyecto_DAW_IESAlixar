package com.iesalixar.playit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.model.UsuarioContent;
import com.iesalixar.playit.repository.UsuarioContentRepository;

@Service
public class UsuarioContentServiceImpl implements UsuarioContentService {

	@Autowired
	UsuarioContentRepository ucRepo;

	@Override
	public UsuarioContent findUsuarioContentByContentAndUsuario(UsuarioContent uc) {

		if (uc.getContent() != null && uc.getUsuario() != null) {
			return ucRepo.findByContentAndUsuario(uc.getContent(), uc.getUsuario());
		}
		return null;
	}

	@Override
	public List<UsuarioContent> findUsuarioByContentUsuario(Usuario user) {
		if(user.getId_usuario() != null) {
			return ucRepo.findAllByUsuario(user);
			
		}
		
		return null;
	}

	@Override
	public List<UsuarioContent> findAllUsuarioContentByUsuarioAndStatus(Usuario usuario, String status) {
		if(usuario.getId_usuario() != null && !status.isEmpty()) {
			List<UsuarioContent> ucs= ucRepo.findAllByUsuario(usuario);
			return ucs;
			
		}
		return null;
	}

	@Override
	public void deleteUsuarioContent(UsuarioContent uc) {
		ucRepo.delete(uc);
		
	}

}
