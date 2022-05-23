package com.iesalixar.playit.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession misession= request.getSession(true);
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JPAUserDetails user = (JPAUserDetails) auth.getPrincipal();
		System.out.println(user.getUsername());
		model.addAttribute("user", user.getUsername());
		
		misession.setAttribute("userOnSession",user.getUsername());
		return "main";
	}
}
