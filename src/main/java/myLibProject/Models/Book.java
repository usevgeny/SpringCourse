package myLibProject.Models;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {

	
	private int id;
	
	@NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 2 and 80 characters")
    private String title;

	
	@NotEmpty(message = "author should not be empty")
    @Size(min = 2, max = 90, message = "Name should be between 2 and 80 characters")
    private String author;
	
	
	@Min(1) 
    private Integer year;
    
    private Integer personId;


	public Book(int id,String title,String author, Integer year, Integer personId) {
		
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.personId = personId;
	}
	
	public Book() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
}
