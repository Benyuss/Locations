package locations;

public class DistanceHelp {

	private double lat; // Latitude
	private double lon; // Longitude
	private int rad; // radius

	public DistanceHelp(Location loc) {
		this.lat = loc.lat;
		this.lon = loc.lon;
		this.rad = loc.rad;
	}

	public double distanceTo(Location loc) { // Distance between "this." and the parameter Location 
		HaversineCalculator hav = new HaversineCalculator();
		double distanceValue = hav.haversineValue(this.lat, this.lon, loc.lat, loc.lon);
		return distanceValue;

	}

	public Contains isContains(Location loc) { // If distance is lower than
												// radius, user-given is
												// contains that location.
												// Semi-Contains can not happen.
		
		double distanceValue = this.distanceTo(loc);
		
		if (distanceValue <= (this.rad - loc.getRad()))
		{
			return Contains.CONTAINS;
		}
		else if (distanceValue > (this.rad + loc.getRad()))
		{
			return Contains.NOT_CONTAINS;
		}
		else //if (distanceValue <= (this.rad + loc.getRad()))
		{
		   return Contains.SEMI_CONTAINS;
		}
	}
}
