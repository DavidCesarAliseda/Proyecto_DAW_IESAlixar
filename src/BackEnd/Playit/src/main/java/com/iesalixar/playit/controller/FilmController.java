package com.iesalixar.playit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iesalixar.playit.dto.FilmDTO;
import com.iesalixar.playit.dto.PersonContentDTO;
import com.iesalixar.playit.model.Content;
import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Person;
import com.iesalixar.playit.model.PersonContent;
import com.iesalixar.playit.model.PersonContentKey;
import com.iesalixar.playit.model.Platform;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.model.UsuarioContent;
import com.iesalixar.playit.model.UsuarioContentKey;
import com.iesalixar.playit.service.CookiesServiceImpl;
import com.iesalixar.playit.service.FilmServiceImpl;
import com.iesalixar.playit.service.GenreServiceImpl;
import com.iesalixar.playit.service.PersonContentServiceImpl;
import com.iesalixar.playit.service.PersonServiceImpl;
import com.iesalixar.playit.service.PlatformServiceImpl;
import com.iesalixar.playit.service.UsuarioContentServiceImpl;
import com.iesalixar.playit.service.UsuarioServiceImpl;

@Controller
public class FilmController {
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

	Film filmAux;
	
	String statusAux;

	@GetMapping("/film")
	public String filmGet(@RequestParam(required = false, name = "deletedFilm") String deletedFilm,
			@RequestParam(required = false, name = "editedFilm") String editedFilm,
			@RequestParam(required = false, name = "addedFilm") String addedFilm, Model model) {
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
		List<Platform> platforms = platformService.getAllPlatforms();

		model.addAttribute("film", film);
		model.addAttribute("platforms", platforms);
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
		filmDB.setPlatform(platformService.getPlatformByID(film.getPlatformId()));
		filmDB.setUrlPlatform(film.getUrlPlatform());

		if (filmService.addFilm(filmDB) == null) {
			return "redirect:/film/add?error=Exist";
		}

		return "redirect:/film?addedFilm=ok";
	}

	@GetMapping("/film/edit")
	public String editFilmGet(@RequestParam(required = true, name = "filmId") String id,
			@RequestParam(required = false, name = "error") String error, Model model) {
		filmAux = new Film();

		Film film = filmService.findFilmById(Long.parseLong(id));
		filmAux.setContentId(film.getContentId());
		filmAux.setCover(film.getCover());
		filmAux.setGenres(film.getGenres());

		List<Platform> platforms = platformService.getAllPlatforms();

		FilmDTO filmDTO = new FilmDTO();

		filmDTO.setTitle(film.getTitle());
		filmDTO.setSynopsis(film.getSynopsis());
		filmDTO.setCountry(film.getCountry());
		filmDTO.setDuration(film.getDuration());
		filmDTO.setValoration(film.getValoration());
		filmDTO.setTrailer(film.getTrailer());
		filmDTO.setPremiere(film.getPremiere());
		filmDTO.setUrlPlatform(film.getUrlPlatform());
		filmDTO.setPlatformId(film.getPlatform().getPlatformId());

		model.addAttribute("filmDTO", filmDTO);
		model.addAttribute("platforms", platforms);
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
		filmDB.setGenres(filmAux.getGenres());
		filmDB.setTitle(film.getTitle());
		filmDB.setSynopsis(film.getSynopsis());
		filmDB.setCountry(film.getCountry());
		filmDB.setDuration(film.getDuration());
		filmDB.setValoration(film.getValoration());
		filmDB.setTrailer(film.getTrailer());
		filmDB.setPremiere(film.getPremiere());
		filmDB.setPlatform(platformService.getPlatformByID(film.getPlatformId()));
		filmDB.setUrlPlatform(film.getUrlPlatform());

		if (filmService.editFilm(filmDB) == null) {
			return "redirect:/film/edit?error=Exist&filmId=" + filmDB.getContentId();
		}

		return "redirect:/film?editedFilm=ok";
	}

	@GetMapping("/film/delete")
	public String deleteFilmGet(@RequestParam(required = false, name = "filmId") String id) {
		Film film = new Film();
		film = filmService.deleteFilm(Long.parseLong(id));
		return "redirect:/film?deletedFilm=ok";
	}

	@GetMapping("/film/genres")
	public String addGenreFilmGet(@RequestParam(required = true, name = "filmId") String id,
			@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "deletedFilm") String deletedFilm, Model model) {

		Film film = filmService.findFilmById(Long.parseLong(id));
		filmAux = new Film();
		filmAux = film;

		List<Genre> genres = film.getGenres();
		List<Genre> allGenres = genreService.getAllGenres();

		model.addAttribute("genres", genres);
		model.addAttribute("deletedFilm", deletedFilm);
		model.addAttribute("error", error);
		model.addAttribute("allGenres", allGenres);

		return "admin/content/filmGenres";
	}

	@PostMapping("/film/genres")
	public String addGenreFilmPost(@RequestParam(required = true, name = "newGenre") String newGenre, Model model) {
		Film filmDB = filmAux;

		List<Genre> genres = filmDB.getGenres();
		Genre genre = genreService.getGenreByID(Long.parseLong(newGenre));

		if (genres.contains(genre)) {
			return "redirect:/film/genres?error=Exist&filmId=" + filmDB.getContentId();
		}

		filmDB.addGenre(genreService.getGenreByID(Long.parseLong(newGenre)));

		if (filmService.editFilm(filmDB) == null) {
			return "redirect:/film/genres?error=error&filmId=" + filmDB.getContentId();
		}

		return "redirect:/film/genres?genreAdded=ok&filmId=" + filmDB.getContentId();
	}

	@GetMapping("/film/genres/delete")
	public String deleteGenreFilmGet(@RequestParam(required = false, name = "genreId") String id) {
		Film filmDB = filmAux;
		filmDB.deleteGenre(genreService.getGenreByID(Long.parseLong(id)));

		if (filmService.editFilm(filmDB) == null) {
			return "redirect:/film/genres?error=error&filmId=" + filmDB.getContentId();
		}

		return "redirect:/film/genres?deletedFilm=ok&filmId=" + filmDB.getContentId();
	}

	@GetMapping("/film/persons")
	public String addPersonFilmGet(@RequestParam(required = true, name = "filmId") String id,
			@RequestParam(required = false, name = "personAdded") String personAdded,
			@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "personDeleted") String personDeleted, Model model) {

		Film film = filmService.findFilmById(Long.parseLong(id));
		filmAux = new Film();
		filmAux = film;

		List<Person> persons = personService.getAllPersons();

		Set<PersonContent> personContents = film.getPersonContent();

		PersonContentDTO pcDTO = new PersonContentDTO();

		model.addAttribute("persons", persons);
		model.addAttribute("pcDTO", pcDTO);
		model.addAttribute("personContents", personContents);
		model.addAttribute("error", error);
		model.addAttribute("personAdded", personAdded);
		model.addAttribute("personDeleted", personDeleted);

		return "admin/content/filmPersons";
	}

	@PostMapping("/film/persons")
	public String addPersonsFilmPost(@ModelAttribute PersonContentDTO pcDTO, Model model) {
		Film filmDB = filmAux;

		/* Set<PersonContent> personContents = filmDB.getPersonContent(); */

		PersonContent personContent = new PersonContent();
		personContent.setRole(pcDTO.getRole());
		personContent.setPerson(personService.getPersonByID(pcDTO.getPersonId()));
		personContent.setContent(filmService.findFilmById(filmDB.getContentId()));

		PersonContentKey pcKey = new PersonContentKey();
		pcKey.setContentId(filmDB.getContentId());
		pcKey.setPersonId(pcDTO.getPersonId());

		personContent.setId(pcKey);

		if (pcService.findPersonContentByContentAndPerson(personContent) != null) {
			return "redirect:/film/persons?error=Exist&filmId=" + filmDB.getContentId();
		}
		filmDB.addPersonContent(personContent);

		if (filmService.editFilm(filmDB) == null) {
			return "redirect:/film/persons?error=error&filmId=" + filmDB.getContentId();
		}
		return "redirect:/film/persons?personAdded=ok&filmId=" + filmDB.getContentId();
	}

	@GetMapping("/film/persons/delete")
	public String deletePersonFilmGet(@RequestParam(required = false, name = "personId") String id) {
		Film filmDB = filmAux;
		Set<PersonContent> personContents = filmDB.getPersonContent();
		PersonContent personContent = new PersonContent();

		for (PersonContent pc : personContents) {
			if (pc.getPerson().getPersonId() == Long.parseLong(id)) {
				personContent = pc;
			}
		}

		pcService.deletePersonContent(personContent);

		return "redirect:/film/persons?personDeleted=ok&filmId=" + filmDB.getContentId();
	}

	@GetMapping("/film/info")
	public String infoFilmGet(@RequestParam(required = true, name = "filmId") String id, HttpServletRequest request,
			Model model) {

		Usuario user = userService.getUserById(Long.parseLong(cookieService.getUserIdOnSession(request)));

		filmAux = new Film();
		Film film = filmService.findFilmById(Long.parseLong(id));
		filmAux = film;

		Set<PersonContent> personContents = film.getPersonContent();
		List<Person> actors = new ArrayList();
		Person director = new Person();

		for (PersonContent personContent : personContents) {
			if (personContent.getRole().equals("Director") || personContent.getRole().equals("Ambos")) {
				director = personContent.getPerson();
			} else if (personContent.getRole().equals("Actor") || personContent.getRole().equals("Ambos")) {
				actors.add(personContent.getPerson());
			}
		}

		Set<UsuarioContent> userContents = film.getUserContents();
		String status = new String();
		for (UsuarioContent usuarioContent : userContents) {
			if (usuarioContent.getId().getUsuarioId().equals(user.getId_usuario())) {
				status = usuarioContent.getStatus();
				System.out.println(status);
			}
		}

		List<Genre> genres = film.getGenres();
		statusAux = status;
		model.addAttribute("status", status);
		model.addAttribute("film", film);
		model.addAttribute("director", director);
		model.addAttribute("actors", actors);
		model.addAttribute("genres", genres);

		return "user/info/film";
	}

	@PostMapping("/film/info")
	public String infoFilmPost(@RequestParam(required = true, name = "status") String status,
			HttpServletRequest request, Model model) {

		Usuario user = userService.getUserById(Long.parseLong(cookieService.getUserIdOnSession(request)));

		UsuarioContentKey ucKey = new UsuarioContentKey();
		ucKey.setContentId(filmAux.getContentId());
		ucKey.setUsuarioId(user.getId_usuario());

		UsuarioContent userContent = new UsuarioContent();
		userContent.setStatus(status);
		userContent.setUsuario(user);
		userContent.setContent(filmAux);
		userContent.setId(ucKey);
		
		if(status.equals("default")) {
			userContent.setStatus(statusAux);
			ucService.deleteUsuarioContent(userContent);
			
		}else {
			ucService.addUsuarioContent(user, filmAux, status);
		}
		

		return "redirect:/film/info?filmId=" + filmAux.getContentId();
	}

	@GetMapping("/moviesC")
	public String genreGet(Model model) {

		Genre genre = genreService.getGenreByName("Drama");
		List<Content> contents = genre.getContents();
		List<Film> films = new ArrayList();

		for (Content content : contents) {
			if (filmService.isFilm(content)) {
				films.add(filmService.findFilmById(content.getContentId()));
			}
		}

		model.addAttribute("films", films);
		return "/user/filmByGenre";
	}

}
