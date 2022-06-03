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

import com.iesalixar.playit.dto.SerieDTO;
import com.iesalixar.playit.model.Serie;
import com.iesalixar.playit.service.SerieServiceImpl;

@Controller
public class SerieController {
	@Autowired
	SerieServiceImpl serieService;
	
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
		model.addAttribute("serie", serie);
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
		serieDB.setTitle(serie.getTitle());
		serieDB.setSynopsis(serie.getSynopsis());
		serieDB.setCountry(serie.getCountry());
		serieDB.setDuration(serie.getDuration());
		serieDB.setSeasons(serie.getSeasons());
		serieDB.setTrailer(serie.getTrailer());
		serieDB.setPremiere(serie.getPremiere());

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
}
