package com.iesalixar.playit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.playit.dto.GenreDTO;
import com.iesalixar.playit.dto.UserAdminDTO;
import com.iesalixar.playit.dto.UserDTO;
import com.iesalixar.playit.model.Genre;
import com.iesalixar.playit.model.Usuario;
import com.iesalixar.playit.service.UsuarioServiceImpl;

@Controller
public class UserController {

	@Autowired
	UsuarioServiceImpl userService;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	@GetMapping("/user")
	public String userGet(@RequestParam(required = false, name = "userDeletedId") String userDeletedId,
			Model model) {
		model.addAttribute("userDeletedId", userDeletedId);
		List<Usuario> users = userService.getAllUsuarios();
		
		model.addAttribute("users", users);
		return "/admin/user";	
	}
	
	@GetMapping("/user/add")
	public String addUGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "userId") String id, Model model) {

		UserAdminDTO user = new UserAdminDTO();

		model.addAttribute("user", user);
		model.addAttribute("error", error);
		model.addAttribute("userId", id);

		return "admin/addUser";
	}

	@PostMapping("/user/add")
	public String addUserPost(@ModelAttribute UserAdminDTO user, Model model) {
		
		Usuario userDB = new Usuario();
		userDB.setUserName(user.getUserName());
		userDB.setNombre(user.getNombre());
		userDB.setApellido1(user.getApellido1());
		userDB.setApellido2(user.getApellido2());
		userDB.setEmail(user.getEmail());
		userDB.setPassword(passEncoder.encode(user.getPassword()));
		userDB.setRole(user.getRole());
			
		if(userService.addUsuario(userDB) == null) {
			return "redirect:/user/add/?error=Exist";
		}
		return "redirect:/user";
	}

	
	@GetMapping("/user/delete")
	public String deleteUserGet(@RequestParam(required = false, name = "userId") String id) {
		Usuario user = new Usuario();
		user = userService.deleteUsuario(Long.parseLong(id));
		Long idGenre = user.getId_usuario();
		return "redirect:/user?userDeletedId=" + idGenre ;
	}

	
	
}
