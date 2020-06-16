package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    public static Logger logger = LoggerFactory.getLogger(PersonController.class);
    private Long id;
    @Autowired
    PersonService personService;
    Person per = new Person();

    @RequestMapping(value = "/",produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listAllUser() {
        List<Person> listUser = personService.findAll();
        if (listUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Person>>(listUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/person/{id}",produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Person> getUser(@PathVariable("id") long id) {
        Optional<Person> p = personService.findById(id);
        return new ResponseEntity<Person>(p.get(), HttpStatus.OK);
    }


    @RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Person> createUser(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
        System.out.println(person.getFisrtName());
        personService.save(person);
        return new ResponseEntity<Person>(person, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Optional<Person> currentPerson = personService.findById(id);
        currentPerson.get().setFisrtName(person.getFisrtName());
        currentPerson.get().setLastName(person.getLastName());
        currentPerson.get().setAge(person.getAge());
        personService.save(currentPerson.get());
        return new ResponseEntity<Person>(currentPerson.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/person", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteAllPerson() {
        personService.deleteAll();
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePerson(@PathVariable("id") long id) {
        Optional<Person> p = personService.findById(id);
        personService.deleteById(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

}
