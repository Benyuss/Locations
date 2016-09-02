package webserver;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dataModels.PairedData;
import geoLocations.DataStream;
import geoLocations.LocationCalculator;
import webserver.database.LocationDB;
import webserver.database.LocationDBRepository;

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
		return "FillDB";
	}

	@Autowired
	private LocationDBRepository repository;

	@PostMapping(value = "/upload", params = "upload")
	public String insertData(@RequestParam("dbfile") MultipartFile file) {
		DataStream datach = new DataStream();
		ArrayList<PairedData> dataList = datach.forwardData(file); //pass a multipart file to the csv scanner which will return an ArrayList filled with PairedData

		for (PairedData data : dataList) { // save that data into the SQL table
			repository.save(new LocationDB(data.getLoc().getLatitude(), data.getLoc().getLongitude(), 
					data.getLoc().getRadius(), data.getGeoHash()));
		}

		return "redirect:index";
	}

	@PostMapping(value = "/choose")
	public String selectrecord(Model model) {
		model.addAttribute("formdata", new LocationDB());
		return "getDB";
	}

	@PostMapping(value = "/geohash")
	public String parseHash(@ModelAttribute("geohash") String geohash, ModelMap model) {
		
		LocationCalculator locCalc = new LocationCalculator();
		
		LocationDB loc = repository.findOne(geohash); //selected one
		
		ArrayList<LocationDB> locations = new ArrayList<LocationDB>(); //all except that
		for (LocationDB temploc : repository.findAll()) { 
			if (geohash == temploc.getGeohash()) {
				continue;
			}
			locations.add(temploc);
		}
		
		ArrayList<PairedData> data = locCalc.calculate(locations, loc);
		
		model.addAttribute("geoItemList", data);
		model.addAttribute("listSize", data.size());
		
		return "geohash";
	}

	@PostMapping(value = "/geohash", params = "reset") // reset form.
	public ModelAndView reset() {
		return new ModelAndView("redirect:index");
	}

	public static void main(String[] args) throws Exception { // start Spring
																// server.
		SpringApplication.run(SpringBootController.class, args);
	}
}
