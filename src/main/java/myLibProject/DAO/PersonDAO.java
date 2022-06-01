package myLibProject.DAO;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import myLibProject.Models.Book;
import myLibProject.Models.Person;
import myLibProject.util.DateUtils;

@Component
public class PersonDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Person> index() {

		return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
	}

	public Person show(int id) {
		// new BeanPropertyRowMapper<>(Person.class)); // either our own mapper either
		// row mapper from Spring

		return jdbcTemplate
				.query("SELECT * FROM person WHERE id=?", new Object[] { id },
						new BeanPropertyRowMapper<>(Person.class)) // either our own mapper either row mapper from
																	// Spring
				.stream().findAny().orElse(null);

	}

	public List<Book> showBooks(int id) {

		return jdbcTemplate.query("SELECT * FROM book where personid=?", new Object[] { id },
				new BeanPropertyRowMapper<>(Book.class));
	}

	
	
	public void update(int id, Person updatedPerson) {
		
		
		try {
			Date convertedDate = DateUtils.formatStringToDate(updatedPerson.getBirthDate(), "yyyy-MM-dd");
			jdbcTemplate.update("UPDATE person SET fullName=?, birthDate=? WHERE id=?",
					updatedPerson.getFullName(), convertedDate, id);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void delete(int id) {
		
		jdbcTemplate.update("DELETE FROM PERSON WHERE id=?", id);
		
	}

	public void save(Person person) {
		
		try {
			Date convertedDate = DateUtils.formatStringToDate(person.getBirthDate(), "yyyy-MM-dd");
			jdbcTemplate.update("INSERT INTO Person(fullname,birthdate) values(?,?)", person.getFullName(), convertedDate);
			
		}  catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
}
