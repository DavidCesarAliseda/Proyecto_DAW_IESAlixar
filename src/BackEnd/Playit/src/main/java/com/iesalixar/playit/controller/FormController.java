package com.iesalixar.playit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.playit.dto.UserDTO;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.service.UsuarioServiceImpl;

@Controller
public class FormController {
	
	@Autowired
	UsuarioServiceImpl userService;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	@GetMapping("/formulario")
	public String userGet(@RequestParam(required = false, name = "error") String error, Model model) {
		model.addAttribute("user", new UserDTO());
		model.addAttribute("error", error);
		return "formulario";
	}
	
	@PostMapping("/formulario")
	public String userPost(@ModelAttribute UserDTO user, Model model) {
		
		if(userService.findUsuarioByEmail(user.getEmail())==null) {
			Usuario userDB = new Usuario();
			userDB.setUserName(user.getUserName());
			userDB.setNombre(user.getNombre());
			userDB.setApellido1(user.getApellido1());
			userDB.setApellido2(user.getApellido2());
			userDB.setEmail(user.getEmail());
			userDB.setPassword(passEncoder.encode(user.getPassword()));
			userDB.setRole("ROLE_USER");
			
			if(userService.insertUsuario(userDB)==null) {
				return "redirect:/formulario?error=UserExist";
			}
		}else {
			return "redirect:/formulario?error=EmailExist";
		}
		
		return "login";
	}
	
}
