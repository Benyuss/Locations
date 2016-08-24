package locations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

public final class GetData {
	// That class handles the "Super" values. These are the user given values.
	// We'll compare every further data with that.
	// Class only contains these values + getters, setters.

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

	private GetData() {
		// I want only one from it so nobody should able to make a new object.
		// Everything is static.
	}

	private static int rad1;
	private static double lat1;
	private static double lon1;

	public static int getRad1() {
		return rad1;
	}

	public static double getLat1() {
		return lat1;
	}

	public static double getLon1() {
		return lon1;
	}

	public static void setRad1(int rad) { // negative or 0 radius value is
											// invalid.
		if (rad > 0) {
			rad1 = rad;
		} else {
			logger.log(Level.DEBUG, "GetData class got an illegal rad value: {}", rad);
			throw new IllegalArgumentException("GetData class got an illegal rad value");
		}
	}

	public static void setLat1(double lat) { // https://en.wikipedia.org/wiki/Latitude
		if (lat > -90 && lat < 90) {
			lat1 = lat;
		} else {
			logger.log(Level.DEBUG, "GetData class got an illegal lat value: {}", lat);
			throw new IllegalArgumentException("GetData class got an illegal lat value");
		}
	}

	public static void setLon1(double lon) { // https://en.wikipedia.org/wiki/Longitude
		if (lon > -180 && lon < 180) {
			lon1 = lon;
		} else {
			logger.log(Level.DEBUG, "GetData class got an illegal lon value: {}", lon);
			throw new IllegalArgumentException("GetData class got an illegal lon value");
		}
	}
}