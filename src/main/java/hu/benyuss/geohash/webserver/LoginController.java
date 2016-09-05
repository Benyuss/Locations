package hu.benyuss.geohash.webserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hu.benyuss.geohash.dataModels.Location;
import hu.benyuss.geohash.geoLocations.LocationCalculator;
import hu.benyuss.geohash.webserver.database.UsersDB;
import hu.benyuss.geohash.webserver.database.UsersDBRepository;

@Controller
public class LoginController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(LoginController.class.getName());
	
	@GetMapping(value = "/login")
	public String loginmain(ModelMap model) {
		model.put("command", new UsersDB());
		return "login";
	}
	
	//create a new account
	@PostMapping(value = "/login", params = "register")
	public String redir2reg(ModelMap model, @ModelAttribute("formdata") UsersDB user) {
		model.put("command", new UsersDB());
		return "register";
	}
	
	@Autowired
	private UsersDBRepository repository;
	
	@PostMapping(value = "/registration")
	public String register(@ModelAttribute("register") UsersDB user , ModelMap model) {
		
		repository.save(new UsersDB(user.getNickname(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail()));
		
		return "redirect:login";
	}

	//login with an existing user
	@PostMapping(value = "/login", params = "login")
	public String login(@ModelAttribute("loginform") UsersDB user , ModelMap model) {
		
		UsersDB existingUser = repository.findOne(user.getNickname());
		
		if (existingUser == null) {
			throw new IllegalArgumentException("There is no existing user with these parameters.");
		}
		else {
			if ( login (user.getPassword(), existingUser.getPassword()) == true) {
				//TODO
			}
			else {
				throw new IllegalArgumentException("Entered password is wrong. Try again!");
			}
			
		}
		
		return "redirect:index";
	}
	
	private boolean login (String currentPW, String realPW) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		return passwordEncoder.matches(currentPW, realPW);
	}
}
