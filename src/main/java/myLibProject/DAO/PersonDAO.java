package myLibProject.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import myLibProject.Models.Book;
import myLibProject.Models.Person;

@Component
public class PersonDAO {
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public List<Person> index(){
		
		return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
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
	
	

}
