package dataModels;

import haversineUtils.Contains;

public final class PairedData{
	// Pairs 2 coordinates with a radius. Use it to keep the data together and organized

	private Location loc;
	private String geoHash;
	private Contains contains = null; // user-given is contains the parsed location or not
	private Double distance = null; // distance between user-given and parsed location.

	public PairedData () {
		//spring requires a default constuctor for this class.
	}
	
	public PairedData(Location loc) {
		this.loc = loc;
	}

	public PairedData(Location loc, String geohash, Contains contains, Double distance) {
		this.loc = loc;
		geoHash = geohash;
		this.contains = contains;
		this.distance = distance;
	}

	public Location getLoc() {
		return loc;
	}
	
	public String getGeoHash() {
		return geoHash;
	}
	
	public void setGeoHash(String geoHash) {
		this.geoHash = geoHash;
	}
	
	public Contains getContains() {
		return contains;
	}
	
	public void setContains(Contains contains) {
		this.contains = contains;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
