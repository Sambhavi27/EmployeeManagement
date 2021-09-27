package com.nagarro.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPLOYEEDETAIL")
public class EmployeeDetail {

	@Id
	private int code;
	@NotBlank(message = "Employee name cannot be empty!!")
	@Size(min = 0, max = 100, message = "Employee name must be less than or equal to 100 characters!!")
	private String name;

	@NotBlank(message = "Employee location cannot be empty!!")
	@Size(min = 0, max = 500, message = "Employee location must be less than or equal to 500 characters!!")
	private String location;

	@Column(length = 100)
	@NotBlank(message = "Email cannot be blank !!")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email!!")
	@Size(min = 0, max = 100, message = "Email must be less than or equal to 100 Characters!!")
	private String email;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotBlank(message = "Date cannot be blank !!")
	@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$", message = "Enter Date in DD/MM/YYYY Format !!")
	private String birthdate;

	public EmployeeDetail(int code, String name, String location, String email, String birthdate) {
		super();
		this.code = code;
		this.name = name;
		this.location = location;
		this.email = email;
		this.birthdate = birthdate;
	}

	public EmployeeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "EmployeeDetail [code=" + code + ", name=" + name + ", location=" + location + ", email=" + email
				+ ", birthdate=" + birthdate + "]";
	}

}
