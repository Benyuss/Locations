package locations;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
class SampleController implements InitLogger{
	
	private static Logger logger = null;
	private double lat;
	private double lon;
	
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

	
	@RequestMapping(value = "/geohash", method = RequestMethod.GET)
	@ResponseBody
	public String geoHash() {
		LocationExecute.calculate();
		return LocationExecute.getNabstring();
	}
	
	@RequestMapping(value = "/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main (String args[]) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
    
    public void setLat (double latitude) {
    	lat = latitude;
    }
    
    public double getLat () {
    	return lat;
    }
    
    public void setLon (double longitude) {
    	lon = longitude;
    }
    
    public double getLon () {
    	return lon;
    }
}