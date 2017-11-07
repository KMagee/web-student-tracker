package com.luv2code.web.jdbc;

public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	
	//CONSTRUCTOR WITH ALL FIELDS
	public Student(int id, String firstName, String lastName, String email) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	//CONSTRUCTOR WITHOUT ID FIELD
	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	//GETTERS AND SETTERS
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
