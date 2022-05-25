package com.iesalixar.playit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iesalixar.playit.dto.GenreDTO;
import com.iesalixar.playit.dto.PlatformDTO;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Platform;
import com.iesalixar.playit.service.CookiesServiceImpl;
import com.iesalixar.playit.service.PlatformServiceImpl;

@Controller
public class PlatformController {

	@Autowired
	PlatformServiceImpl platformService;

	Platform platformAux;

	String userSession;

	@GetMapping("/platform")
	public String platfomrGet(@RequestParam(required = false, name = "deletedPlatform") String deletedPlatform,
			@RequestParam(required = false, name = "editedPlatform") String editedPlatform, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		List<Platform> platforms = platformService.getAllPlatforms();

		model.addAttribute("deletedPlatform", deletedPlatform);
		model.addAttribute("platforms", platforms);
		model.addAttribute("editedPlatform", editedPlatform);
		return "admin/platform";
	}

	@GetMapping("/platform/add")
	public String addPlatfomrGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "platformName") String nombre, Model model) {
		System.out.println(userSession);
		PlatformDTO platform = new PlatformDTO();
		model.addAttribute("platform", platform);
		model.addAttribute("error", error);
		model.addAttribute("platformName", nombre);

		return "admin/addPlatform";
	}

	@PostMapping("/platform/add")
	public String addPlatfomrGet(@ModelAttribute PlatformDTO platform,
			@RequestParam(required = true, name = "logo") MultipartFile logo, Model model) {

		if (!logo.isEmpty()) {
			/*
			 * Path directorioImagenes= Paths.get("src//main//resources//static/img");
			 * String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 */
			String rutaAbsoluta = "C://Users//david//Documents//Recursos";

			try {
				byte[] bytesImg = logo.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + logo.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Platform platformDB = new Platform();
		platformDB.setName(platform.getName());
		platformDB.setLogo(logo.getOriginalFilename());
		String namePlatform = platform.getName();
		if (platformService.addPlatform(platformDB) == null) {
			System.out.println(platform.getName());
			return "redirect:/platform/add?error=Existe&platformName=" + namePlatform;
		}

		return "redirect:/platform";
	}

	@GetMapping("/platform/delete")
	public String deletePlatformGet(@RequestParam(required = false, name = "platformId") String id) {
		Platform platform = new Platform();
		platform = platformService.deletePlatform(Long.parseLong(id));
		Long idPlatform = platform.getPlatformId();
		return "redirect:/platform?deletedPlatform=ok";
	}

	/*
	 * En el formualrio de editar tiene que aparecer la imagen.
	 */

	@GetMapping("/platform/edit")
	public String editPlatformGet(@RequestParam(required = true, name = "platformId") String id,
			@RequestParam(required = false, name = "error") String error, Model model) {
		platformAux = new Platform();

		Platform platform = platformService.getPlatformByID(Long.parseLong(id));
		platformAux.setPlatformId(platform.getPlatformId());
		platformAux.setLogo(platform.getLogo());

		model.addAttribute("platform", platform);
		model.addAttribute("error", error);
		return "admin/editPlatform";
	}

	@PostMapping("/platform/edit")
	public String editPlatformPost(@ModelAttribute PlatformDTO platform,
			@RequestParam(required = true, name = "logo") MultipartFile logo, Model model) {

		Platform platformDB = new Platform();
		if (!logo.isEmpty()) {
			/*
			 * Path directorioImagenes= Paths.get("src//main//resources//static/img");
			 * String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 */
			String rutaAbsoluta = "C://Users//david//Documents//Recursos";

			try {
				byte[] bytesImg = logo.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + logo.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				platformDB.setLogo(logo.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			platformDB.setLogo(platformAux.getLogo());
		}

		platformDB.setPlatformId(platformAux.getPlatformId());
		platformDB.setName(platform.getName());

		if (platformService.editPlatform(platformDB) != null) {
			return "redirect:/platform/edit?error=Existe&platformId="+platformDB.getPlatformId();
		}

		return "redirect:/platform/editedPlatform=ok";
	}

}
