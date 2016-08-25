package locations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

public class Location {
	// We'll parse files and get lat-lon-rad values from them. This class and
	// it's objects is used to store this data.

	private static Logger logger = null;
	static {

		try {
			InitLogger.initialize();
		} catch (FileNotFoundException e) {
			logger.log(Level.ERROR,
					"Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.ERROR,
					"Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		}

		logger = InitLogger.logger[0];
	}

	protected double lat; // Latitude
	protected double lon; // Longitude
	protected int rad; // radius

	public Location(double lat, double lon, int radius) {
		this.lat = lat;
		this.lon = lon;
		this.rad = radius;
	}
	
	public int getRad() {
		return rad;
	}
}