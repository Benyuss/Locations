package hu.benyuss.geohash.webserver;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import hu.benyuss.geohash.dataModels.Location;
import hu.benyuss.geohash.dataModels.PairedData;
import hu.benyuss.geohash.geoLocations.DataStream;
import hu.benyuss.geohash.geoLocations.LocationCalculator;
import hu.benyuss.geohash.webserver.database.LocationDB;
import hu.benyuss.geohash.webserver.database.LocationDBRepository;

@Controller
@EnableJpaRepositories
@EnableAutoConfiguration
@SpringBootApplication
class SpringBootController {

	static final Logger logger = (Logger) LogManager.getLogger(SpringBootController.class.getName());
	
	@GetMapping(value = "/index")
	public String mappingHelp(ModelMap model) {
		return "index";
	}

	@PostMapping(value = "/upload")
	public String fillDB(ModelMap model) {
		return "fillDBWithCSV";
	}

	@Autowired
	private LocationDBRepository repository;

	@PostMapping(value = "/upload", params = "upload")
	public String insertData(@RequestParam("dbfile") MultipartFile file) {
		DataStream datach = new DataStream();
		ArrayList<PairedData> dataList = datach.forwardData(file); 
		// pass a multipart file to csv scanner which will return
			//an ArrayList filled with PairedData

		for (PairedData data : dataList) { // save that data into the SQL table
			repository.save(new LocationDB(data.getLoc().getLatitude(), data.getLoc().getLongitude(),
					data.getLoc().getRadius(), data.getGeoHash()));
		}

		return "redirect:index";
	}
	
	@PostMapping(value = "/custom-input") // Get values from textboxes.
	public String getUserInput(ModelMap model) {
		model.put("command", new Location() );
		return "userinput";
	}

	@PostMapping(value = "/custom-input", params = "submit")
	public String customInput (@ModelAttribute("custom") Location customInput, ModelMap model ) {
		LocationCalculator locCalc = new LocationCalculator();
		String geohash = locCalc.generateGeohash(customInput.getLatitude(), customInput.getLongitude(), customInput.getRadius());
		
		if (repository.findOne(geohash)  == null) {
			repository.save(new LocationDB(customInput.getLatitude(), customInput.getLongitude(), customInput.getRadius(), geohash));
		}
		else {
			new IllegalArgumentException("Already contains that record.");
		}
		
		return "redirect:index";
	}
	
	@PostMapping(value = "/custom-input", params = "index")
	public ModelAndView custinput2index() {
		return new ModelAndView("redirect:index");
	}
	
	
	@PostMapping(value = "/choose")
	public String selectrecord(Model model) {
		model.addAttribute("formdata", new LocationDB());
		return "dropdownDBChoose";
	}
	
	@PostMapping(value = "/choose", params = "index")
	public ModelAndView choose2index() {
		return new ModelAndView("redirect:index");
	}

	@PostMapping(value = "/geohash")
	public String parseHash(@ModelAttribute("geohash") String geohash, ModelMap model) {

		LocationCalculator locCalc = new LocationCalculator();

		LocationDB loc = repository.findOne(geohash); // selected one

		ArrayList<LocationDB> locations = new ArrayList<LocationDB>(); // all
																		// except
																		// that
		for (LocationDB temploc : repository.findAll()) { //fill that list with that data but exclude the chosen one.
			if (geohash == temploc.getGeohash()) {
				continue;
			}
			locations.add(temploc);
		}

		ArrayList<PairedData> data = locCalc.calculate(locations, loc); // for html (geohash.jsp) parse

		model.addAttribute("geoItemList", data);
		model.addAttribute("listSize", data.size());
		
		model.addAttribute("chosen", loc);
		
		return "geohash";
	}
	
	@PostMapping(value = "/geohash", params = "index") // back to index with keeping DB alive.
	public ModelAndView geohash2index() {
		return new ModelAndView("redirect:index");
	}
	
	@PostMapping(value = "/geohash", params = "reset") // reset database and back to index.
	public ModelAndView reset() {
		repository.deleteAll();
		return new ModelAndView("redirect:index");
	}

	public static void main(String[] args) throws Exception { 
		// start Spring server.										
		SpringApplication.run(SpringBootController.class, args);
	}
}
