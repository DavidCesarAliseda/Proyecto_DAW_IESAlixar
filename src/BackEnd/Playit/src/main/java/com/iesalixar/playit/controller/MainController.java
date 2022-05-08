package com.iesalixar.playit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.playit.model.JPAUserDetails;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.service.UsuarioServiceImpl;

@Controller
public class MainController {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@RequestMapping("/")
	public String home(Model model, @RequestParam(required=false,name="usuario") Usuario usuario) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		/*if (auth.getPrincipal() != "anonymousUser") {
			System.out.println("Poco a poco");
		}*/
		
		model.addAttribute("contenido","INICIO");
		return "index";
	}
}
