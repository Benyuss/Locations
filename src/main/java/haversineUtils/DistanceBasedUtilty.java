package haversineUtils;

import dataModels.Location;

public class DistanceBasedUtilty {

	private double lat; // Latitude
	private double lon; // Longitude
	private int rad; // radius

	public DistanceBasedUtilty(Location location) {
		this.lat = location.getLatitude();
		this.lon = location.getLongitude();
		this.rad = location.getRadius();
	}

	public double distanceTo(Location loc) { // Distance between "this." and the
												// parameter Location
		HaversineCalculator hav = new HaversineCalculator();
		double distanceValue = hav.haversineValue(this.lat, this.lon, loc.getLatitude(), loc.getLongitude());
		return distanceValue;

	}

	public Contains isContains(Location loc) { // open a math book at circles
												// for explanation.

		double distanceValue = this.distanceTo(loc);

		if (distanceValue <= (this.rad - loc.getRadius())) {
			return Contains.CONTAINS;
		} else if (distanceValue > (this.rad + loc.getRadius())) {
			return Contains.NOT_CONTAINS;
		} else // if (distanceValue <= (this.rad + loc.getRad()))
		{
			return Contains.SEMI_CONTAINS;
		}
	}
}
