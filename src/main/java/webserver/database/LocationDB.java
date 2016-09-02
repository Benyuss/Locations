package webserver.database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LocationDB {

	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:mem:locationdb"; //in memory database.

	static final String USER = "sa";
	static final String PASS = "";

	@Id
	private String geohash;
	private Double latitude;
	private Double longitude;
	private int radius;

	public LocationDB() {
	}

	public LocationDB(Double latitude, Double longitude, int radius, String geohash) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.geohash = geohash;
	}

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}