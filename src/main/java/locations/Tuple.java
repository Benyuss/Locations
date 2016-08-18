package locations;

public final class Tuple {
	// Pairs 2 coordinates with a radius. Use it to keep the data together from
	// csv files.

	public Tuple() {

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

	public Double getFirstCoord() {
		return firstCoordinate;
	}

	public Double getSecondCoord() {
		return secondCoordinate;
	}

	public void setFirstCoord(double cord) {
		firstCoordinate = cord;
	}

	public void setSecondCoord(double cord) {
		secondCoordinate = cord;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
