package com.iesalixar.playit.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iesalixar.playit.dto.GenreDTO;
import com.iesalixar.playit.model.Content;
import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.JPAUserDetails;
import com.iesalixar.playit.model.Serie;
import com.iesalixar.playit.service.FilmServiceImpl;
import com.iesalixar.playit.service.GenreServiceImpl;
import com.iesalixar.playit.service.SerieServiceImpl;
import com.iesalixar.playit.service.UsuarioServiceImpl;


@Controller
public class MainController {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	GenreServiceImpl genreService;
	
	@Autowired
	FilmServiceImpl filmService;
	
	@Autowired
	SerieServiceImpl serieService;
	
	@RequestMapping("/main")
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
		String user_role = "ROLE_USER"	;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JPAUserDetails user = (JPAUserDetails) auth.getPrincipal();
		model.addAttribute("user", user.getUsername());
		
		Cookie userOnSession = new Cookie("userId", usuarioService.getUsuarioByUserName(user.getUsername()).getId_usuario().toString());
		response.addCookie(userOnSession);
		
		String role = usuarioService.getUsuarioByUserName(user.getUsername()).getRole();
		if(role.equals(user_role)) {
			
			Genre drama = genreService.getGenreByName("Drama");
		
			List<Film> films = filmService.getAllFilmsByGenre(drama);
			List<Serie> series = serieService.getAllSeriesByGenre(drama);
			for (int i = 0; i < 5; i++) {
				series.addAll(series);
			}
			
			for (int i = 0; i < 5; i++) {
				films.addAll(films);
			}
			

			model.addAttribute("films", films);
			model.addAttribute("series", series);
			
			return "user/index_user";
		}

		return "main";
	}
	
	@PostMapping("/main/user")
	public String addGenrePost(@ModelAttribute GenreDTO genre, Model model) {
		
		

		return "redirect:/genre?adeddGenre=ok";
	}
}
