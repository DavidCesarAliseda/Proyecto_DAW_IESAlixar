package com.iesalixar.playit.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.model.UsuarioContent;
import com.iesalixar.playit.service.CookiesServiceImpl;
import com.iesalixar.playit.service.FilmServiceImpl;
import com.iesalixar.playit.service.GenreServiceImpl;
import com.iesalixar.playit.service.PersonContentServiceImpl;
import com.iesalixar.playit.service.PersonServiceImpl;
import com.iesalixar.playit.service.PlatformServiceImpl;
import com.iesalixar.playit.service.UsuarioContentServiceImpl;
import com.iesalixar.playit.service.UsuarioServiceImpl;


@Controller
public class UserListController {
	@Autowired
	FilmServiceImpl filmService;

	@Autowired
	PlatformServiceImpl platformService;

	@Autowired
	GenreServiceImpl genreService;

	@Autowired
	PersonServiceImpl personService;

	@Autowired
	PersonContentServiceImpl pcService;

	@Autowired
	CookiesServiceImpl cookieService;

	@Autowired
	UsuarioServiceImpl userService;

	@Autowired
	UsuarioContentServiceImpl ucService;
	
	@GetMapping("/myList")
	public String listmGet() {
		
		
		
		return "/user/list";
	}
	
	@GetMapping("/myList/films")
	public String filmListGet(HttpServletRequest request, Model model) {
		
		Usuario user = userService.getUserById(Long.parseLong(cookieService.getUserIdOnSession(request)));
		
		
		List<Film> pendientes = filmService.getAllFilmsByUserAndStatus(user, "pendiente");
		List<Film> vistas = filmService.getAllFilmsByUserAndStatus(user, "vista");
		List<Film> favoritas = filmService.getAllFilmsByUserAndStatus(user, "favorita");
		
		model.addAttribute("pendientes", pendientes);
		model.addAttribute("vistas", vistas);
		model.addAttribute("favoritas", favoritas);
		
		return "/user/filmList";
	}
}
