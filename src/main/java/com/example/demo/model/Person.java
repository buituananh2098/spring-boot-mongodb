package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Person implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String fisrtName;
	private String lastName;
	private int age;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public Person(String fisrtName, String lastName, int age, long id) {
		super();
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.age = age;
		this.id = id;
	}
	public Person() {
		super();
		id=0;
	}
	public String getFisrtName() {
		return fisrtName;
	}
	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
