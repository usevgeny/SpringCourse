package myLibProject.Models;

import java.sql.Date;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Person {
	
	private int id;
	
	@NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 80, message = "Name should be between 2 and 80 characters")
	@Pattern(regexp="[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "your full name should be in this format: FirstName Patronymic LastName)")
    private String fullName;

    @NotEmpty(message = "BirthDate should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")//@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", message = "your birth-date should be in this format: yyyy-MM-dd)")
    private String birthDate;

	public Person() {
		
	}

	public Person(int id, String fullName, String birthDate) {
		
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
    
	

    
    
    
}
