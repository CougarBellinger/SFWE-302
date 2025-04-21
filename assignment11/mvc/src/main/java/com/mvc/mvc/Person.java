package com.mvc.mvc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "people")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Name cannot be empty.")
	private String name;
	
	@NotEmpty(message = "Email cannot be empty.")
	private String email;
	
	@Min(value = 18, message = "Age must be greater than 17.")
	private int age;
	
	public Person() {}
	
	public Person (String name, String email, int age) {
		this.name = name;
		this.email=email;
		this.age=age;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}
