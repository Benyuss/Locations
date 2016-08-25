package locations;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

@Controller
@EnableAutoConfiguration
class SpringBootController {

	static final Logger logger = (Logger) LogManager.getLogger(SpringBootController.class.getName());

	@SuppressWarnings("unused")
	private static Tuple userData;

	@GetMapping(value = "/geohash") // Get values from textboxes.
	public String getUserInput(ModelMap model) {
		model.put("command", userData = new Tuple());
		return "UserInput";
	}

	@PostMapping(value = "/geohash", params = "SubmitWithDefault") // Insert
																	// default
																	// values.
	public String defaultUserInput(ModelMap model) {
		model.put("command", userData = new Tuple(48.104564, 20.800041, 6));
		return "UserInput";
	}

	@PostMapping(value = "/geohash", params = "Submit") // Start calculating
														// with file +
														// userinput.
	public String printHash(@ModelAttribute("user") Tuple tuple, ModelMap model, MultipartFile file) {

		// GetData.setLat1(tuple.getFirstCoordinate()); // user data
		// GetData.setLon1(tuple.getSecondCoordinate()); // user data
		// GetData.setRad1(tuple.getRadius()); // user data
		CSVScanner scanner = new CSVScanner(); // file data parser. If no file
												// is selected, it will choose
												// coordinates.csv from local
												// machine.
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// validateFile(file);
				InputStream inputStream = new ByteArrayInputStream(bytes);
				scanner.scan(inputStream);
			} catch (IOException e) {
				logger.error("Can't parse data.");
				e.printStackTrace();
			}
		} else {
			try {
				File file1 = new File("coordinates.csv");
				FileInputStream input = new FileInputStream(file1);
				scanner.scan(input);
			} catch (IOException e) {
				logger.error("Can't parse default csv file.");
				e.printStackTrace();
			}
		}

		LocationExecute.calculate(scanner, tuple);
		model.addAttribute("geoItemList", LocationExecute.getTempArray());
		model.addAttribute("listSize", LocationExecute.getTempArray().size());
		LocationExecute.tempArray = new ArrayList<Tuple>();

		// If you want to see user input in the first row of the html table,
		// change foreach begin value from 1 -> 0
		return "Geohash";
	}

	@PostMapping(value = "/geohash", params = "reset") // reset form.
	public ModelAndView method() {
		logger.debug("\b\b\b\b\bNew Data will come. Form has reseted.");
		return new ModelAndView("redirect:geohash");
	}

	public static void main(String args[]) throws Exception { // start Spring
																// server.
		SpringApplication.run(SpringBootController.class, args);
	}
}
