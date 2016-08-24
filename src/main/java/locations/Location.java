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

	public double distanceTo(Location loc) { // Distance between that and the
												// parameter Location (it's the
												// user given at the current
												// use)
		HaversineCalculator hav = new HaversineCalculator();
		double distanceValue = hav.haversineValue(this.lat, this.lon, loc.lat, loc.lon);
		return distanceValue;

	}

	public int getRad() {
		return rad;
	}

	public Contains isContains(Location loc) { // If distance is lower than
												// radius, user-given is
												// contains that location.
												// Semi-Contains can not happen.
		double distanceValue = this.distanceTo(loc);
		double value = distanceValue + loc.rad;
		if (value < this.rad) {
			return Contains.CONTAINS;
		} else if (value > this.rad) {
			return Contains.NOT_CONTAINS;
		} else {
			return Contains.SEMI_CONTAINS;
		}
	}
}