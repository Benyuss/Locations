package locations;

public class Location {
	// We'll parse files and get lat-lon-rad values from them. This class and
	// it's objects is used to store this data.

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