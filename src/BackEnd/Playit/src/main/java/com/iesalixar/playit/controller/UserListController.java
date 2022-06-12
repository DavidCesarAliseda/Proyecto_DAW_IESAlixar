package com.iesalixar.playit.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Serie;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.model.UsuarioContent;
import com.iesalixar.playit.service.CookiesServiceImpl;
import com.iesalixar.playit.service.FilmServiceImpl;
import com.iesalixar.playit.service.GenreServiceImpl;
import com.iesalixar.playit.service.PersonContentServiceImpl;
import com.iesalixar.playit.service.PersonServiceImpl;
import com.iesalixar.playit.service.PlatformServiceImpl;
import com.iesalixar.playit.service.SerieServiceImpl;
import com.iesalixar.playit.service.UsuarioContentServiceImpl;
import com.iesalixar.playit.service.UsuarioServiceImpl;


@Controller
public class UserListController {
	@Autowired
	FilmServiceImpl filmService;
	
	@Autowired
	SerieServiceImpl serieService;

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
		
		
		/*List<Film> pendientes = filmService.getAllFilmsByUserAndStatus(user, "pendiente");
		List<Film> vistas = filmService.getAllFilmsByUserAndStatus(user, "vista");
		List<Film> favoritas = filmService.getAllFilmsByUserAndStatus(user, "favorita");
		*/
		List<Film> pendientes = new ArrayList();
		List<Film> vistas = new ArrayList();
		List<Film> favoritas = new ArrayList();
		Set<UsuarioContent> userContents = user.getUserContents();
		
		for (UsuarioContent usuarioContent : userContents) {
			if(filmService.isFilm(usuarioContent.getContent())) {
				Film film = filmService.findFilmById(usuarioContent.getContent().getContentId());
				if(usuarioContent.getStatus().equals("pendiente")) {
					pendientes.add(film);
				}else if(usuarioContent.getStatus().equals("favorita")){
					favoritas.add(film);
				}else if (usuarioContent.getStatus().equals("vista")) {
					vistas.add(film);
				}
			}
		}
		
		model.addAttribute("pendientes", pendientes);
		model.addAttribute("vistas", vistas);
		model.addAttribute("favoritas", favoritas);
		
		return "/user/filmList";
	}
	
	@GetMapping("/myList/series")
	public String serieListGet(HttpServletRequest request, Model model) {
		
		Usuario user = userService.getUserById(Long.parseLong(cookieService.getUserIdOnSession(request)));
		
		List<Serie> pendientes = new ArrayList();
		List<Serie> vistas = new ArrayList();
		List<Serie> favoritas = new ArrayList();
		List<Serie> siguiendo = new ArrayList();
		Set<UsuarioContent> userContents = user.getUserContents();
		
		for (UsuarioContent usuarioContent : userContents) {
			if(serieService.isSerie(usuarioContent.getContent())) {
				Serie serie = serieService.getSerieByID(usuarioContent.getContent().getContentId());
				if(usuarioContent.getStatus().equals("pendiente")) {
					pendientes.add(serie);
				}else if(usuarioContent.getStatus().equals("favorita")){
					favoritas.add(serie);
				}else if (usuarioContent.getStatus().equals("vista")) {
					vistas.add(serie);
				}else if (usuarioContent.getStatus().equals("siguiendo")) {
					siguiendo.add(serie);
				}
			}
		}
		
		model.addAttribute("pendientes", pendientes);
		model.addAttribute("vistas", vistas);
		model.addAttribute("favoritas", favoritas);
		
		return "/user/serieList";
	}
}
