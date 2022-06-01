package myLibProject.Controllers;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import myLibProject.DAO.PersonDAO;
import myLibProject.Models.Person;
import myLibProject.util.DateUtils;

@Controller
@RequestMapping("/subscribers")
public class PersonController {
	
	private final PersonDAO personDao;
	
	
	@Autowired
	public PersonController(PersonDAO personDao) {
		super();
		this.personDao = personDao;
	}



	@GetMapping()
	public String index(Model model) {

		model.addAttribute("subscribers", personDao.index());
		
		return "subscribers/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable int id, Model model) {

		model.addAttribute("subscriber", personDao.show(id));
		model.addAttribute("hiredBooks", personDao.showBooks(id));
		
		return "subscribers/show";
		
	}
	
	@GetMapping("/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		
		
		return "subscribers/new";
	}
	
	
	@PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
		
		
        if (bindingResult.hasErrors())
            return "subscribers/new";

        personDao.save(person);
        return "redirect:/subscribers";
    }

	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		
		if(personDao.show(id)==null) {
			
			return "redirect:/";
		}
		
		model.addAttribute("person",personDao.show(id));
		
		
		return ("subscribers/edit");
	}
	
	
	@PatchMapping("/{id}/update")
	public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		
		if(personDao.show(id)==null) {
			
			return "redirect:/";
		}
		
		//Date convertedDate = DateUtils.formatStringToDate(person.getBirthDate(), "yyyy-MM-dd");
		personDao.update(id, person);
		
		
		return "redirect:/subscribers";
	}
	

	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		
		personDao.delete(id);
	
		return "redirect:/subscribers";
	}
	

}
