package com.iesalixar.playit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iesalixar.playit.dto.PersonContentDTO;
import com.iesalixar.playit.dto.SerieDTO;
import com.iesalixar.playit.model.Film;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Person;
import com.iesalixar.playit.model.PersonContent;
import com.iesalixar.playit.model.PersonContentKey;
import com.iesalixar.playit.model.Platform;
import com.iesalixar.playit.model.Serie;

import com.iesalixar.playit.service.GenreServiceImpl;
import com.iesalixar.playit.service.PersonContentServiceImpl;
import com.iesalixar.playit.service.PersonServiceImpl;
import com.iesalixar.playit.service.PlatformServiceImpl;
import com.iesalixar.playit.service.SerieServiceImpl;

@Controller
public class SerieController {
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
	
	Serie serieAux;

	@GetMapping("/serie")
	public String genreGet(@RequestParam(required = false, name = "deletedSerie") String deletedSerie,
			@RequestParam(required = false, name = "editedSerie") String editedSerie ,
			@RequestParam(required = false, name = "addedSerie") String addedSerie ,Model model) {
		List<Serie> series = serieService.getAllSeries();

		model.addAttribute("series", series);
		model.addAttribute("deletedSerie", deletedSerie);
		model.addAttribute("editedSerie", editedSerie);
		model.addAttribute("addedSerie", addedSerie);
		return "/admin/serie";
	}

	@GetMapping("/serie/add")
	public String addSerieGet(@RequestParam(required = false, name = "error") String error,
			 Model model) {

		SerieDTO serie = new SerieDTO();
		List<Platform> platforms = platformService.getAllPlatforms();
		
		model.addAttribute("serie", serie);
		model.addAttribute("platforms", platforms);
		model.addAttribute("error", error);
		return "admin/addSerie";
	}
	
	@PostMapping("/serie/add")
	public String addSeriePost(@ModelAttribute SerieDTO serie,
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
		
		Serie serieDB = new Serie();
		
		serieDB.setTitle(serie.getTitle());
		serieDB.setSynopsis(serie.getSynopsis());
		serieDB.setCountry(serie.getCountry());
		serieDB.setDuration(serie.getDuration());
		serieDB.setSeasons(serie.getSeasons());
		serieDB.setTrailer(serie.getTrailer());
		serieDB.setPremiere(serie.getPremiere());
		serieDB.setCover(cover.getOriginalFilename());
		serieDB.setPlatform(platformService.getPlatformByID(serie.getPlatformId()));
		serieDB.setUrlPlatform(serie.getUrlPlatform());
		
		if(serieService.addSerie(serieDB) == null) {
			return "redirect:/serie/add?error=Exist";
		}
		
		return "redirect:/serie?addedSerie=ok";
	}
	
	@GetMapping("/serie/edit")
	public String editSerieGet(@RequestParam(required = true, name = "serieId") String id,
			@RequestParam(required = false, name = "error") String error, Model model) {
		serieAux = new Serie();

		Serie serie = serieService.getSerieByID(Long.parseLong(id));
		serieAux.setContentId(serie.getContentId());
		serieAux.setCover(serie.getCover());
		serieAux.setGenres(serie.getGenres());
		
		SerieDTO serieDTO = new SerieDTO();
		
		serieDTO.setTitle(serie.getTitle());
		serieDTO.setSynopsis(serie.getSynopsis());
		serieDTO.setCountry(serie.getCountry());
		serieDTO.setDuration(serie.getDuration());
		serieDTO.setSeasons(serie.getSeasons());
		serieDTO.setTrailer(serie.getTrailer());
		serieDTO.setPremiere(serie.getPremiere());
		serieDTO.setUrlPlatform(serie.getUrlPlatform());
		serieDTO.setPlatformId(serie.getPlatform().getPlatformId());

		model.addAttribute("serieDTO", serieDTO);
		model.addAttribute("serie", serie);
		model.addAttribute("error", error);
		return "admin/editSerie";
	}
	
	@PostMapping("/serie/edit")
	public String editSeriePost(@ModelAttribute SerieDTO serie,
			@RequestParam(required = true, name = "cover") MultipartFile cover, Model model) {

		Serie serieDB = new Serie();
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
				serieDB.setCover(cover.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			serieDB.setCover(serieAux.getCover());
		}

		serieDB.setContentId(serieAux.getContentId());
		serieDB.setGenres(serieAux.getGenres());
		serieDB.setTitle(serie.getTitle());
		serieDB.setSynopsis(serie.getSynopsis());
		serieDB.setCountry(serie.getCountry());
		serieDB.setDuration(serie.getDuration());
		serieDB.setSeasons(serie.getSeasons());
		serieDB.setTrailer(serie.getTrailer());
		serieDB.setPremiere(serie.getPremiere());
		serieDB.setPlatform(platformService.getPlatformByID(serie.getPlatformId()));
		serieDB.setUrlPlatform(serie.getUrlPlatform());

		if (serieService.editSerie(serieDB) == null) {
			return "redirect:/serie/edit?error=Exist&serieId="+serieDB.getContentId();
		}

		return "redirect:/serie?editedSerie=ok";
	}
	
	@GetMapping("/serie/delete")
	public String deleteSerieGet(@RequestParam(required = false, name = "serieId") String id) {
		Serie serie = new Serie();
		serie = serieService.deleteSerie(Long.parseLong(id));
		return "redirect:/serie?deletedSerie=ok";
	}
	
	@GetMapping("/serie/genres")
	public String addGenreSerieGet(@RequestParam(required = true, name = "serieId") String id, 
			@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "deletedSerie") String deletedSerie,Model model) {
		
		Serie serie = serieService.getSerieByID(Long.parseLong(id));
		serieAux = new Serie();
		serieAux = serie;
		
		List<Genre> genres = serie.getGenres();
		List<Genre> allGenres = genreService.getAllGenres();
		
		model.addAttribute("deletedSerie", deletedSerie);
		model.addAttribute("genres", genres);
		model.addAttribute("error", error);
		model.addAttribute("allGenres", allGenres);
		
		return "admin/content/serieGenres";
	}
	
	@PostMapping("/serie/genres")
	public String addGenreSeriePost(@RequestParam(required = true, name = "newGenre") String newGenre, Model model) {
		Serie serieDB = serieAux;
		
		List<Genre> genres = serieDB.getGenres();
		Genre genre = genreService.getGenreByID(Long.parseLong(newGenre));
		
		if(genres.contains(genre)) {
			return "redirect:/serie/genres?error=Exist&serieId="+serieDB.getContentId();
		}
		
		serieDB.addGenre(genreService.getGenreByID(Long.parseLong(newGenre)));
		
		if (serieService.editSerie(serieDB) == null) {
			return "redirect:/serie/genres?error=error&serieId="+serieDB.getContentId();
		}
		
		return "redirect:/serie/genres?genreAdded=ok&serieId="+serieDB.getContentId();
	}
	
	@GetMapping("/serie/genres/delete")
	public String deleteGenreSerieGet(@RequestParam(required = false, name = "genreId") String id) {
		Serie serieDB = serieAux;
		serieDB.deleteGenre(genreService.getGenreByID(Long.parseLong(id)));
		
		if (serieService.editSerie(serieDB) == null) {
			return "redirect:/serie/genres?error=error&serieId="+serieDB.getContentId();
		}
		
		return "redirect:/serie/genres?deletedSerie=ok&serieId="+serieDB.getContentId();
	}
	
	@GetMapping("/serie/persons")
	public String addPersonSerieGet(@RequestParam(required = true, name = "serieId") String id, 
			@RequestParam(required = false, name = "personAdded") String personAdded,
			@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "personDeleted") String personDeleted, Model model) {
		
		Serie serie = serieService.getSerieByID(Long.parseLong(id));
		serieAux = new Serie();
		serieAux = serie;
	
		List<Person> persons = personService.getAllPersons();
		
		Set<PersonContent> personContents = serie.getPersonContent();
		
		PersonContentDTO pcDTO = new PersonContentDTO();
		
		model.addAttribute("persons", persons);
		model.addAttribute("pcDTO", pcDTO);
		model.addAttribute("personContents", personContents);
		model.addAttribute("error", error);
		model.addAttribute("personAdded", personAdded);
		model.addAttribute("personDeleted", personDeleted);
		
		return "admin/content/seriePersons";
	}
	
	@PostMapping("/serie/persons")
	public String addPersonsSeriePost(@ModelAttribute PersonContentDTO pcDTO, Model model) {
		Serie serieDB = serieAux;
		
		/*Set<PersonContent> personContents = serieDB.getPersonContent();*/
		
		PersonContent personContent = new PersonContent();
		personContent.setRole(pcDTO.getRole());
		personContent.setPerson(personService.getPersonByID(pcDTO.getPersonId()));
		personContent.setContent(serieService.getSerieByID(serieDB.getContentId()));
		
		PersonContentKey pcKey = new PersonContentKey();
		pcKey.setContentId(serieDB.getContentId());
		pcKey.setPersonId(pcDTO.getPersonId());
		
		personContent.setId(pcKey);
		
		if (pcService.findPersonContentByContentAndPerson(personContent) != null) {
			return "redirect:/serie/persons?error=Exist&serieId="+serieDB.getContentId();
		}
		serieDB.addPersonContent(personContent);
		
		if (serieService.editSerie(serieDB) == null) {
			return "redirect:/serie/persons?error=error&serieId="+serieDB.getContentId();
		}
		return "redirect:/serie/persons?personAdded=ok&serieId="+serieDB.getContentId();
	}
	
	@GetMapping("/serie/persons/delete")
	public String deletePersonSerieGet(@RequestParam(required = false, name = "personId") String id) {
		Serie serieDB = serieAux;
		Set<PersonContent> personContents = serieDB.getPersonContent();
		PersonContent personContent = new PersonContent();
		
		for (PersonContent pc : personContents) {
			if(pc.getPerson().getPersonId() == Long.parseLong(id)) {
				personContent = pc;
			}
		}
		
		pcService.deletePersonContent(personContent);
		
		return "redirect:/serie/persons?personDeleted=ok&serieId="+serieDB.getContentId();
	}
}
