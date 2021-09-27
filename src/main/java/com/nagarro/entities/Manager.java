package com.nagarro.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MANAGER")
public class Manager {

	@Id
	@NotBlank(message="UserId cannot be empty!!")
	@Size(min=5 ,max=50,message="UserId must be between 5-50 characters!!")
	private String id;
	
	@NotBlank(message="Password cannot be empty!!")
	@Size(min=5 ,max=50,message="Password must be between 5-50 characters!!")
	private String password;
	
		
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", password=" + password + "]";
	}

}
