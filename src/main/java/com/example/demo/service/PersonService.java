	package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository
public interface PersonService extends JpaRepository<Person, Long>{

}
