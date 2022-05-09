package com.iesalixar.playit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iesalixar.playit.service.UsuarioServiceImpl;

@Controller
public class MainController {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
}
