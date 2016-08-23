package locations;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@EnableAutoConfiguration
class SpringBootController implements InitLogger {
	
	private static Logger logger = null;
	
	static {
		try {
			InitLogger.initialize();
		} catch (FileNotFoundException e) {
			logger.log(Level.ERROR, "Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.ERROR, "Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		}
		
		logger = InitLogger.logger[0];
	}	
	
	@GetMapping(value="/geohash")
	public String getUserInput(ModelMap model) {
		    	model.put("command", new Tuple());
		return "UserInput";
	}
	
	@PostMapping(value="/geohash", params="SubmitWithDefault")
	public String defaultUserInput(ModelMap model) {
			model.put("command", tupleFill (48.104564, 20.800041, 6) );
		return "UserInput";
	}
	
	@PostMapping(value = "/geohash", params="Submit")
	public String printHash(@ModelAttribute("user")Tuple tuple,ModelMap model) {
		GetData.setLat1(tuple.getFirstCoordinate());
		GetData.setLon1(tuple.getSecondCoordinate());
		GetData.setRad1(tuple.getRadius());
		
		LocationExecute.calculate();
		model.addAttribute("geoItemList", LocationExecute.getTupleList());
		model.addAttribute("listSize", LocationExecute.getTupleList().size());
		return "Geohash";
	}
	
    @RequestMapping(value = "/geohash", params="reset", method = RequestMethod.POST)
    public ModelAndView method() {
            return new ModelAndView("redirect:geohash");

    }
	
	
    public static void main (String args[]) throws Exception {
        SpringApplication.run(SpringBootController.class, args);
    }
    
    private Tuple tupleFill (double lat, double lon, int rad) {
    	Tuple tuple = new Tuple();
		tuple.setFirstCoordinate(lat); 
		tuple.setSecondCoordinate(lon);
		tuple.setRadius(rad);
		return tuple;
    }
    
}