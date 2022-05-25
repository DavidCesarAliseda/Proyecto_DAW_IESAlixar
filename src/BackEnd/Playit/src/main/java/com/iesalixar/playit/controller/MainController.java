package com.iesalixar.playit.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iesalixar.playit.model.JPAUserDetails;
import com.iesalixar.playit.service.UsuarioServiceImpl;

@Controller
public class MainController {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@RequestMapping("/main")
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
				
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JPAUserDetails user = (JPAUserDetails) auth.getPrincipal();
		model.addAttribute("user", user.getUsername());
		
		Cookie userOnSession = new Cookie("userId", usuarioService.getUsuarioByUserName(user.getUsername()).getId_usuario().toString());
		response.addCookie(userOnSession);

		return "main";
	}
}
