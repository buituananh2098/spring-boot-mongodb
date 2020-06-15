package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@Controller
public class PersonController {
	public static Logger logger = LoggerFactory.getLogger(PersonController.class);
	private Long id;
	@Autowired
	PersonService personService;
	Person per = new Person();
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listAllContact(Model model) {
		List<Person> listContact = personService.findAll();
		model.addAttribute("person", listContact);
//		new ResponseEntity<List<Person>>(listContact, HttpStatus.OK)
		return "form";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String customerForm(Model model) {
		model.addAttribute("person", new Person());
		return "create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String customerSubmit(@ModelAttribute Person person, Model model) {
		model.addAttribute("person", person);
		Person p = new Person();
		p.setFisrtName(person.getFisrtName());
		p.setLastName(p.getLastName());
		p.setAge(p.getAge());
		personService.save(p);
		return "redirect:/";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String findContact(@PathVariable("id") long id, Model model) {
		Person p = personService.getOne(id);
		if (p == null) {
			ResponseEntity.notFound().build();
		}
		per.setId(id);
		model.addAttribute("person", p);
		return "edit";
	}

	@RequestMapping(value = "/person/", method = RequestMethod.POST)
	public Person saveContact(@Valid @RequestBody Person person) {
		return personService.save(person);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update( @RequestParam String fisrtName,
			@RequestParam String lastName, @RequestParam int age, @ModelAttribute Person p, Model model) {
		model.addAttribute("person", p);
		Optional<Person> person = personService.findById(per.getId());
		person.get().setLastName(lastName);
		person.get().setFisrtName(fisrtName);
		person.get().setAge(age);
		personService.save(person.get());
		return "redirect:/";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteContact(@PathVariable(value = "id") Long id) {
		Optional<Person> p = personService.findById(id);
		personService.delete(p.get());
		return "redirect:/";
	}
}
