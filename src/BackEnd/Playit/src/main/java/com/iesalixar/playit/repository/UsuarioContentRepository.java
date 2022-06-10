package com.iesalixar.playit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Content;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.model.UsuarioContent;

@Repository
public interface UsuarioContentRepository extends JpaRepository<UsuarioContent, Long>{
	public UsuarioContent findByContentAndUsuario(Content content, Usuario usuario);
	public List<UsuarioContent> findAllByUsuario(Usuario usuario);
	public List<UsuarioContent> findAllByUsuarioAndStatus(Usuario usuario, String status);
}
