package com.iesalixar.playit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.playit.dto.PersonDTO;
import com.iesalixar.playit.model.Person;
import com.iesalixar.playit.service.PersonServiceImpl;



@Controller
public class PersonController {
	
	@Autowired
	PersonServiceImpl personService;
	
	Person personAux;
	
	@GetMapping("/person")
	public String genreGet(@RequestParam(required = false, name = "editedPerson") String editedPerson,
			@RequestParam(required = false, name = "deletedPerson") String deletedPerson, 
			@RequestParam(required = false, name = "addedPerson") String addedPerson , Model model) {
		List<Person> persons = personService.getAllPersons();
		
		model.addAttribute("persons", persons);
		model.addAttribute("editedPerson", editedPerson);
		model.addAttribute("deletedPerson", deletedPerson);
		model.addAttribute("addedPerson", addedPerson);
		return "/admin/person";
	}
	
	@GetMapping("/person/add")
	public String addPersonGet(@RequestParam(required = false, name = "error") String error, Model model) {

		PersonDTO person = new PersonDTO();

		model.addAttribute("person", person);
		model.addAttribute("error", error);

		return "admin/addPerson";
	}

	@PostMapping("/person/add")
	public String addPersonPost(@ModelAttribute PersonDTO person, Model model) {
		
		Person personDB = new Person();
		personDB.setName(person.getName());
		personDB.setSurname1(person.getSurname1());
		personDB.setSurname2(person.getSurname2());
		personDB.setBirthDate(person.getBirthDate());

		if (personService.addPerson(personDB) == null) {
			return "redirect:/person/add?error=Exist";
		}

		return "redirect:/person?addedPerson=ok";
	}
	
	@GetMapping("/person/delete")
	public String deletePersoneGet(@RequestParam(required = false, name = "personId") String id) {
		Person person = new Person();
		person = personService.deletePerson(Long.parseLong(id));
		return "redirect:/person?deletedPerson=ok";
	}
	
	@GetMapping("/person/edit")
	public String editPersonGet(@RequestParam(required = true, name = "personId") String personId, Model model) {
		personAux = new Person();

		Person person = personService.getPersonByID(Long.parseLong(personId));
		personAux.setPersonId(person.getPersonId());

		model.addAttribute(person);
		return "admin/editPerson";
	}

	@PostMapping("/person/edit")
	public String editPersonPost(@ModelAttribute PersonDTO person, Model model) {
		Person personDB = new Person();

		personDB.setPersonId(personAux.getPersonId());
		personDB.setName(person.getName());
		personDB.setBirthDate(person.getBirthDate());
		personDB.setSurname1(person.getSurname1());
		personDB.setSurname2(person.getSurname2());
		if (personService.editPerson(personDB) == null) {
			return "redirect:/person/edit?error=Exist&personId="+personDB.getPersonId();
		}

		return "redirect:/person?editedPerson=ok";
	}
}
