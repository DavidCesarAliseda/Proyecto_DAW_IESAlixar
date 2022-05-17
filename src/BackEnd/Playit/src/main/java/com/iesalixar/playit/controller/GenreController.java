package com.iesalixar.playit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.playit.dto.GenreDTO;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.service.GenreServiceImpl;

@Controller
public class GenreController {
	@Autowired
	GenreServiceImpl genreService;

	Genre genreAux;

	@GetMapping("/genre")
	public String genreGet(@RequestParam(required = false, name = "genreDeleted") String genreDeletedId, 
			@RequestParam(required = false, name = "genreEdited") String genreEditedId ,Model model) {

		List<Genre> genres = genreService.getAllGenres();

		model.addAttribute("genres", genres);
		model.addAttribute("genreDeletedId ", genreDeletedId);
		model.addAttribute("genreEdited", genreEditedId);
		return "/admin/genre";
	}

	@GetMapping("/genre/add")
	public String addGenreGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "genreName") String nombre, Model model) {

		GenreDTO genre = new GenreDTO();

		model.addAttribute("genre", genre);
		model.addAttribute("error", error);
		model.addAttribute("genreName", nombre);

		return "admin/addGenre";
	}

	@PostMapping("/genre/add")
	public String addGenrePost(@ModelAttribute GenreDTO genre, Model model) {
		System.out.println(genre.getName());
		Genre genreDB = new Genre();
		genreDB.setName(genre.getName());

		if (genreService.addGenre(genreDB) == null) {
			System.out.println(genre.getName());
			return "redirect:/genre/add?error=Existe&genreName=" + genre.getName();
		}

		return "redirect:/genre";
	}

	@GetMapping("/genre/delete")
	public String deleteGenreGet(@RequestParam(required = false, name = "genreId") String id) {
		System.out.println(id);
		Genre genre = new Genre();
		genre = genreService.deleteGenre(Long.parseLong(id));
		return "redirect:/genre?genreDeleted=" + genre.getGenreId();
	}

	@GetMapping("/genre/edit")
	public String editGenreGet(@RequestParam(required = true, name = "genreId") String id, Model model) {
		genreAux = new Genre();

		Genre genre = genreService.getGenreByID(Long.parseLong(id));
		genreAux.setGenreId(genre.getGenreId());

		model.addAttribute(genre);
		return "admin/editGenre";
	}

	@PostMapping("/genre/edit")
	public String editGenrePost(@ModelAttribute GenreDTO genre, Model model) {
		Genre genreDB = new Genre();
		
		System.out.println(genreAux.getGenreId());
		genreDB.setGenreId(genreAux.getGenreId());
		genreDB.setName(genre.getName());

		if (genreService.addGenre(genreDB) != null) {
			return "redirect:/genre?genreEdited=" + genreDB.getGenreId() ;
		}

		return "redirect:/genre";
	}

}
