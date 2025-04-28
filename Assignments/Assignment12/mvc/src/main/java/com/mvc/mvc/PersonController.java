package com.mvc.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Person> people = personService.findAllPeople();
		
		model.addAttribute("people", people);
		return "home";
	}
	
	@GetMapping("/add-person")
	public String addPersonForm(Model model) {
		model.addAttribute("person", new Person());
		return "addPerson";
	}
	
	@PostMapping("/people")
	public String addPerson(@Valid Person person, BindingResult errors) {
		if (errors.hasErrors()) {
			return "addPerson";
		}
		personService.addPerson(person);
		return "redirect:/";
	}

	@PostMapping("/api/people")
	public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
		Person savedPerson = personService.addPerson(person);
		return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
	}
}
