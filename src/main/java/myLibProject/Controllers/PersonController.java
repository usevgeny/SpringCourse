package myLibProject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myLibProject.DAO.PersonDAO;
import myLibProject.Models.Person;

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
	
	
	

}
