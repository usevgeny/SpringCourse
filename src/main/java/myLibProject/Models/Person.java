package myLibProject.Models;

import java.sql.Date;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
	
	private int id;
	
	@NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 80 characters")
	@Pattern(regexp="[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "your full name should be in this format: FirstName Patronymic LastName)")
    private String fullName;

    @NotEmpty(message = "Email should not be empty")
    @Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", message = "your birth-date should be in this format: yyyy-MM-dd)")
    private Date birthDate;

	public Person() {
		
	}

	public Person(int id, String fullName, Date birthDate) {
		
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
    
	//project.setStartDate(DateUtils.formatStringToDate("2022-04-13", "yyyy-MM-dd"));

    
    
    
}
