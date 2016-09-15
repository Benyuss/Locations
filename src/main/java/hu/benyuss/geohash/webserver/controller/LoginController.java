package hu.benyuss.geohash.webserver.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hu.benyuss.geohash.dataModels.User;
import hu.benyuss.geohash.webserver.database.DBUtils;
import hu.benyuss.geohash.webserver.database.UsersDB;
import hu.benyuss.geohash.webserver.database.UsersDBRepository;

@Controller
public class LoginController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(LoginController.class.getName());
	
	@GetMapping(value = "/login")
	public String loginmain(ModelMap model) {
		model.put("command", new User());
		DBUtils utility = new DBUtils();
		
		return "login";
	}
	
	//create a new account
	@PostMapping(value = "/login", params = "register")
	public String redir2reg(ModelMap model, @ModelAttribute("formdata") UsersDB user) {
		model.put("command", new User());//TODO 
		return "register";
	}
	
	@Autowired
	private UsersDBRepository repository;
	
	@PostMapping(value = "/registration")
	public String register(@ModelAttribute("register") User user , ModelMap model) {
		
		DBUtils utility = new DBUtils();
		
		String hashedPW = utility.encodePW(user.getPassword(), user.getPasswordValidator());
		
		UsersDB storedUser = new UsersDB(user.getFirstName(), user.getLastName(), user.getEmail(), user.getNickname() , hashedPW);
		storedUser.addAuth();
		user = null; //security reasons, marked for GC
		
		try {
		repository.save(storedUser);
		}
		catch (Exception e) {
			logger.error("Unable to register. Username or email already in use.");
		}
		
		return "redirect:login";
	}

	//login with an existing user
	@PostMapping(value = "/login", params = "login")
	public String login(@ModelAttribute("loginform") User user , ModelMap model) {
		
		UsersDB existingUser = repository.findByNicknameOrEmail(user.getNickname(), user.getEmail());
		
		if (existingUser == null) {
			throw new IllegalArgumentException("There is no existing user with these parameters.");
		}
		else {
			DBUtils utils = new DBUtils();
			if ( utils.login (user.getPassword(), existingUser.getPassword()) == true) {
				logger.error("welcome " + existingUser.getNickname() + "!");
//				Authentication auth = new Authentication();
				
				//STATE????? TODO
				
				SecurityContextHolder.getContext().setAuthentication((Authentication) existingUser);
			}
			else {
				throw new IllegalArgumentException("Entered password is wrong. Try again!");
			}
			
//			AuthenticationManagerBuilder
			
		}
		
		return "redirect:index";
	}
	
}
