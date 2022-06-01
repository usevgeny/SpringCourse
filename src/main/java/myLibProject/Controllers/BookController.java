package myLibProject.Controllers;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import myLibProject.DAO.BookDAO;
import myLibProject.DAO.PersonDAO;
import myLibProject.Models.Book;
import myLibProject.Models.Person;

@Controller
@RequestMapping("/books")
public class BookController {

	private final BookDAO bookDao;
	private final PersonDAO personDao;

	public BookController(BookDAO bookDao, PersonDAO personDao) {
		super();
		this.bookDao = bookDao;
		this.personDao = personDao;
	}

	@GetMapping()
	public String index(Model model) {

		model.addAttribute("books", bookDao.index());

		return "/books/index";
	}

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {

		if (bookDao.show(id) == null) {
			return "redirect:/books";
		}

		if (bookDao.show(id).getPersonId() != null) {

		

			model.addAttribute("subscriber", personDao.show(bookDao.show(id).getPersonId()));
		}
		
		
		model.addAttribute("book", bookDao.show(id));
		model.addAttribute("subscribers", personDao.index());
		
		
		return "/books/show";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {

		model.addAttribute("book", bookDao.show(id));

		return "/books/edit";
	}

	
	
	@GetMapping("/new")
	public String newBook(@ModelAttribute("book")Book book) {

		return "/books/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			return "redirect:/books/new";
		}
		System.out.println(book.getYear());
		bookDao.save(book);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/update")
	public String update(@PathVariable("id") int id, @ModelAttribute("book") Book updatedBook) {

		
		bookDao.update(id, updatedBook);

		return "redirect:/books/{id}";
	}
	
	
	@PatchMapping("/{id}/unassign")
	public String unassign(@PathVariable("id") int id) {

		bookDao.bookUnassign(id, bookDao.show(id));

		return "redirect:/books/{id}";
	}
	
	@PatchMapping("/{id}/assign")
	public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
		
		if(bookDao.show(id)==null) {
			return "redirect:/books";
		}
		if(personDao.show(person.getId())==null) {
			return "redirect:/books";
		}
		
		bookDao.bookAssignTo(id, person.getId());
		

		return "redirect:/books/{id}";
	}
	
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		
		bookDao.delete(id);
	
		return "redirect:/books";
	}

}
