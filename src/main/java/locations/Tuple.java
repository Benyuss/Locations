package locations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public final class Tuple {
	// Pairs 2 coordinates with a radius. Use it to keep the data together and
	// organized.

	static final Logger logger = (Logger) LogManager.getLogger(Tuple.class.getName());

	private Double firstCoordinate; // lat
	private Double secondCoordinate; // lon
	private int radius;
	private String geoHash;
	private Contains contains; // user-given is contains the current location or
								// not
	private double distance; // distance between user-given and parsed location.

	public Tuple() {
		// If we just want to initialize a Tuple but without values.
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

	public void setFirstCoordinate(double lat) { // Same as GetData class
		if (lat > -90 && lat < 90) {
			firstCoordinate = lat;
		} else {
			logger.error("got an illegal lat value: {}", lat);
			throw new IllegalArgumentException(getClass() + "got an illegal lat value");
		}
	}

	public void setSecondCoordinate(double lon) { // Same as GetData class
		if (lon >= -180 && lon <= 180) {
			secondCoordinate = lon;
		} else {
			logger.error("got an illegal lon value: {}", lon);
			throw new IllegalArgumentException(getClass() + "got an illegal lon value");
		}
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) { // Same as GetData class
		if (radius > 0) {
			this.radius = radius;
		} else {
			logger.error(getClass() + " got a negative radius value: {}", radius);
			throw new IllegalArgumentException(getClass() + "got an illegal rad value");
		}
	}
}
