package locations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

public class Location {
//Location class which is used to instantiation	Location objects. Printing is disabled. Look at line 253 for further info.
	
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
	
	protected double lat; //Latitude
	protected double lon; //Longitude
	protected int rad;	//object radius
	
	public Location (double lat, double lon, int radius) {
		this.lat = lat;
		this.lon = lon;
		this.rad = radius;
	}

	public double distanceTo (Location loc) {
		HaversineCalculator hav = new HaversineCalculator();
		double	distanceValue = hav.haversineValue(this.lat, this.lon, loc.lat, loc.lon);
		logger.log(Level.DEBUG, this.lat +" " + this.lon + " with rad: " + this.rad + "\t" + loc.lat +
				" " + loc.lon + " with rad: " + loc.rad);
		logger.log(Level.DEBUG, "value of distance is: " + distanceValue);
		return distanceValue;
				
	}
	
	public int getRad() {
		return rad;
	}

	public Contains isContains (Location loc) {
		double distanceValue = this.distanceTo(loc);
		double value = distanceValue + loc.rad ;
		if (value < this.rad) {
			logger.log(Level.DEBUG, Contains.CONTAINS);
			return Contains.CONTAINS;
		}
		else if (value > this.rad) {
			logger.log(Level.DEBUG, Contains.NOT_CONTAINS);
			return Contains.NOT_CONTAINS;
		}
		else {
			logger.log(Level.DEBUG, Contains.SEMI_CONTAINS);
			return Contains.SEMI_CONTAINS;
		}	
	}
}