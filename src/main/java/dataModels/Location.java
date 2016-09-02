package dataModels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Location {
	// We'll parse files and get lat-lon-rad values from them. This class and
	// it's objects is used to store this data.
	private static final Logger logger = (Logger) LogManager.getLogger(Location.class.getName());

	private Double latitude; // Latitude
	private Double longitude; // Longitude
	private int radius; // radius

	public Location(Double lat, Double lon, int radius) {
		setLatitude(lat);
		setLongitude(lon);
		setRadius(radius);
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(double lat) {
		if (lat > -90 && lat < 90) {
			latitude = lat;
		} else {
			logger.error("got an illegal lat value: {}", lat);
			throw new IllegalArgumentException(getClass() + "got an illegal lat value");
		}
	}

	public void setLongitude(double lon) {
		if (lon >= -180 && lon <= 180) {
			longitude = lon;
		} else {
			logger.error("got an illegal lon value: {}", lon);
			throw new IllegalArgumentException(getClass() + "got an illegal lon value");
		}
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		if (radius > 0) {
			this.radius = radius;
		} else {
			logger.error(getClass() + " got a negative radius value: {}", radius);
			throw new IllegalArgumentException(getClass() + "got an illegal rad value");
		}
	}
}