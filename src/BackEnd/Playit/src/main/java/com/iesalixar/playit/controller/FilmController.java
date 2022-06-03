package com.iesalixar.playit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iesalixar.playit.dto.FilmDTO;
import com.iesalixar.playit.dto.PlatformDTO;
import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Platform;
import com.iesalixar.playit.service.FilmServiceImpl;

@Controller
public class FilmController {
	@Autowired
	FilmServiceImpl filmService;
	
	Film filmAux;

	@GetMapping("/film")
	public String genreGet(@RequestParam(required = false, name = "deletedFilm") String deletedFilm,
			@RequestParam(required = false, name = "editedFilm") String editedFilm ,
			@RequestParam(required = false, name = "addedFilm") String addedFilm ,Model model) {
		List<Film> films = filmService.getAllFilms();

		model.addAttribute("films", films);
		model.addAttribute("deletedFilm", deletedFilm);
		model.addAttribute("editedFilm", editedFilm);
		model.addAttribute("addedFilm", addedFilm);
		return "/admin/film";
	}

	@GetMapping("/film/add")
	public String addFilmGet(@RequestParam(required = false, name = "error") String error, Model model) {

		FilmDTO film = new FilmDTO();
		model.addAttribute("film", film);
		model.addAttribute("error", error);
		return "admin/addFilm";
	}
	
	@PostMapping("/film/add")
	public String addFilmPost(@ModelAttribute FilmDTO film,
			@RequestParam(required = true, name = "cover") MultipartFile cover, Model model) {
		
		if (!cover.isEmpty()) {
			/*
			 * Path directorioImagenes= Paths.get("src//main//resources//static/img");
			 * String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 */
			String rutaAbsoluta = "C://Users//david//Documents//Recursos//Covers";

			try {
				byte[] bytesImg = cover.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + cover.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Film filmDB = new Film();
		
		filmDB.setTitle(film.getTitle());
		filmDB.setSynopsis(film.getSynopsis());
		filmDB.setCountry(film.getCountry());
		filmDB.setDuration(film.getDuration());
		filmDB.setValoration(film.getValoration());
		filmDB.setTrailer(film.getTrailer());
		filmDB.setPremiere(film.getPremiere());
		filmDB.setCover(cover.getOriginalFilename());
		
		if(filmService.addFilm(filmDB) == null) {
			return "redirect:/film/add?error=Exist";
		}
		
		return "redirect:/film?addedFilm=ok";
	}
	
	@GetMapping("/film/edit")
	public String editFilmGet(@RequestParam(required = true, name = "filmId") String id, 
			@RequestParam(required = false, name = "error") String error, Model model) {
		filmAux = new Film();

		Film film = filmService.getFilmByID(Long.parseLong(id));
		filmAux.setContentId(film.getContentId());
		filmAux.setCover(film.getCover());

		model.addAttribute("film", film);
		model.addAttribute("error", error);
		return "admin/editFilm";
	}
	
	@PostMapping("/film/edit")
	public String editFilmPost(@ModelAttribute FilmDTO film,
			@RequestParam(required = true, name = "cover") MultipartFile cover, Model model) {

		Film filmDB = new Film();
		if (!cover.isEmpty()) {
			/*
			 * Path directorioImagenes= Paths.get("src//main//resources//static/img");
			 * String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 */
			String rutaAbsoluta = "C://Users//david//Documents//Recursos//Covers";

			try {
				byte[] bytesImg = cover.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + cover.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				filmDB.setCover(cover.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			filmDB.setCover(filmAux.getCover());
		}

		filmDB.setContentId(filmAux.getContentId());
		filmDB.setTitle(film.getTitle());
		filmDB.setSynopsis(film.getSynopsis());
		filmDB.setCountry(film.getCountry());
		filmDB.setDuration(film.getDuration());
		filmDB.setValoration(film.getValoration());
		filmDB.setTrailer(film.getTrailer());
		filmDB.setPremiere(film.getPremiere());

		if (filmService.editFilm(filmDB) == null) {
			return "redirect:/film/edit?error=Exist&filmId="+filmDB.getContentId();
		}

		return "redirect:/film?editedFilm=ok";
	}
	
	@GetMapping("/film/delete")
	public String deleteFilmGet(@RequestParam(required = false, name = "filmId") String id) {
		Film film = new Film();
		film = filmService.deleteFilm(Long.parseLong(id));
		return "redirect:/film?deletedFilm=ok";
	}
}
