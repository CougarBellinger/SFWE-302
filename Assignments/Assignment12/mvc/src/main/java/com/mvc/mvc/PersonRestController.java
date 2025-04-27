package com.mvc.mvc;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {
	
	private final PersonService personService;
	
	public PersonRestController(PersonService personService) {
		this.personService = personService;
	}
	
	@ResponseBody
	@GetMapping(value = "/people", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAllPeople() {
		return personService.findAllPeople();
	}
	
}
