package com.iesalixar.playit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
