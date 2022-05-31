package myLibProject.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import myLibProject.Models.Book;
import myLibProject.Models.Person;

@Component
public class BookDAO {
	
	
	private final JdbcTemplate jdbcTeplate;
	
	@Autowired
	public BookDAO(JdbcTemplate jdbcTeplate) {

		this.jdbcTeplate = jdbcTeplate;
	}
	
	
	public List<Book> index(){
	
	return jdbcTeplate.query("SELECT * from Book", new BeanPropertyRowMapper<>(Book.class));
		
	
	}
	

	
	
	public Book show(int id) {
		// new BeanPropertyRowMapper<>(Person.class)); // either our own mapper either
		// row mapper from Spring
		
		return jdbcTeplate
				.query("SELECT * FROM Book WHERE id=?", new Object[] { id },
						new BeanPropertyRowMapper<>(Book.class)) // either our own mapper either row mapper from
																	// Spring
				.stream().findAny().orElse(null);

	}
	
	public void save(Book book) {
		
		jdbcTeplate.update("INSERT INTO book(title=?, author=?, year=?)", book.getTitle(), book.getAuthor(), book.getYear());
		
		
	}
	
	
	public void update(int id, Book updatedBook) {
		
		jdbcTeplate.update("UPDATE book SET title=?, author=?, year=? WHERE id=?", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), updatedBook.getId());
		
		
	}
	
	
	public void bookUnassign(int id, Book book) {
		
		jdbcTeplate.update("UPDATE book SET personId=null WHERE id=?", book.getId());
		
		
	}
	
	public void delete(int id) {
		
		jdbcTeplate.update("DELETE book WHERE id=?");
		
		
	}


	public void bookAssignTo(Book book, int personId) {
		jdbcTeplate.update("UPDATE book SET personId=? WHERE id=?", book.getId(), personId);
		
	}
	

}
