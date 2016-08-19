package locations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

public final class Tuple {
	// Pairs 2 coordinates with a radius. Use it to keep the data together from
	// csv files.
	
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
	
	
	public Tuple () {
		
	}
	
	public Tuple(Double first, Double second, int radius) {
		firstCoordinate = first;
		secondCoordinate = second;
		this.radius = radius;
	}

	public Tuple(Double first, Double second, int radius, String geohash) {
		firstCoordinate = first;
		secondCoordinate = second;
		this.radius = radius;
		geoHash = geohash;
	}

	public Tuple(Double first, Double second, int radius, String geohash, Contains contains, double distance) {
		firstCoordinate = first;
		secondCoordinate = second;
		this.radius = radius;
		geoHash = geohash;
		this.contains = contains;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Tuple [firstCoordinate=" + firstCoordinate + ", secondCoordinate=" + secondCoordinate + ", radius="
				+ radius + "geoHash=" + geoHash + "]";
	}

	private Double firstCoordinate;
	private Double secondCoordinate;
	private int radius;
	private String geoHash;
	private Contains contains;
	private double distance;

	public double getDistance() {
		return distance;
	}

	public Contains getContains() {
		return contains;
	}

	public String getGeoHash() {
		return geoHash;
	}

	public Double getFirstCoordinate() {
		return firstCoordinate;
	}

	public Double getSecondCoordinate() {
		return secondCoordinate;
	}

	public void setFirstCoordinate(double lat) {
		if (lat > -90 && lat < 90) {
    		firstCoordinate = lat;
    	}
    	else {
			logger.log(Level.DEBUG, "got an illegal lat value: {}", lat);
			throw new IllegalArgumentException(getClass() +"got an illegal lat value");
		}
	}

	public void setSecondCoordinate(double lon) {
		if (lon >= -180 && lon <= 180) {
    		secondCoordinate = lon;
    	}
    	else {
			logger.log(Level.DEBUG, "got an illegal lon value: {}", lon);
			throw new IllegalArgumentException(getClass() +"got an illegal lon value");
		}
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		if (radius > 0) {
			this.radius = radius;
		}
		else {
			logger.log(Level.DEBUG, getClass() + " got a negative radius value: {}", radius);
			throw new IllegalArgumentException(getClass() +"got an illegal rad value");
		}
	}
}
