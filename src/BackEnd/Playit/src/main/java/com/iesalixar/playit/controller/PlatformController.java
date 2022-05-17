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

import com.iesalixar.playit.dto.PlatformDTO;
import com.iesalixar.playit.model.Platform;
import com.iesalixar.playit.service.PlatformServiceImpl;

@Controller
public class PlatformController {

	@Autowired
	PlatformServiceImpl platformService;

	@GetMapping("/platform")
	public String platfomrGet(Model model) {

		List<Platform> platforms = platformService.getAllPlatforms();

		model.addAttribute("platforms", platforms);

		return "admin/platform";
	}

	@GetMapping("/platform/add")
	public String addPlatfomrGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "platformName") String nombre ,Model model){
		
		
		PlatformDTO platform = new PlatformDTO();
		model.addAttribute("platform", platform);
		model.addAttribute("error", error);
		model.addAttribute("platformName", nombre);
		
		return "admin/addPlatform";
	}

	@PostMapping("/platform/add")
	public String addPlatfomrGet(@ModelAttribute PlatformDTO platform,  
			@RequestParam(required = true, name="logo") MultipartFile logo, Model model){
		
		if(!logo.isEmpty()) {
			Path directorioImagenes= Paths.get("src//main//resources//static/img");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte [] bytesImg = logo.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + logo.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Platform platformDB = new Platform();
		platformDB.setName(platform.getName());
		platformDB.setLogo(logo.getOriginalFilename());

		if (platformService.addPlatform(platformDB) == null) {
			System.out.println(platform.getName());
			return "redirect:/platform/add?error=Existe&platformeName=" + platform.getName();
		}

		return "redirect:/platform";
	}
}

