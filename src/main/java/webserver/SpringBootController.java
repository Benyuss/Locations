package webserver;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dataModels.Location;
import dataModels.PairedData;
import geoLocations.DataStream;

@Controller
@EnableAutoConfiguration
class SpringBootController {

	static final Logger logger = (Logger) LogManager.getLogger(SpringBootController.class.getName());

	@GetMapping(value = "/geohash") // Get values from textboxes.
	public String getUserInput(ModelMap model) {
		model.put("command", new Location() );
		return "UserInput";
	}

	@PostMapping(value = "/geohash", params = "SubmitWithDefault") // Insert
																	// default
																	// values.
	public String defaultUserInput(ModelMap model) {
		model.put("command", new Location(48.104564, 20.800041, 6) );
		return "UserInput";
	}

	@PostMapping(value = "/geohash", params = "Submit") // Start calculating
														// with file +
														// userinput.
	public String printTable(@ModelAttribute("user") Location userInput , ModelMap model,
			MultipartFile file) {

		DataStream datach = new DataStream();
		ArrayList<PairedData> currentData = datach.forwardData(file, new PairedData (userInput));

		model.addAttribute("geoItemList", currentData);
		model.addAttribute("listSize", currentData.size());

		// If you want to see user input in the first row of the html table,
		// change foreach begin value from 1 -> 0 (geohash.jsp)
		return "Geohash";
	}

	@PostMapping(value = "/geohash", params = "reset") // reset form.
	public ModelAndView reset() {
		return new ModelAndView("redirect:geohash");
	}

	public static void main(String args[]) throws Exception { // start Spring
																// server.
		SpringApplication.run(SpringBootController.class, args);
	}
}
