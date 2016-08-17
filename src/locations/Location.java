public class Location {
//Location class which is used to instantiation	Location objects. Printing is disabled. Look at line 253 for further info.
	
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
		System.out.println(this.lat +" " + this.lon + " with rad: " + this.rad + "\t" + loc.lat +
							" " + loc.lon + " with rad: " + loc.rad);
		return distanceValue;
				
	}
	
	public int getRad() {
		return rad;
	}

	public Contains isContains (Location loc) {
		double distanceValue = this.distanceTo(loc);
		double value = distanceValue + loc.rad ;
		if (value < this.rad) {
			return Contains.CONTAINS;
		}
		else if (value > this.rad) {
			return Contains.NOT_CONTAINS;
		}
		else {
			return Contains.SEMI_CONTAINS;
		}	
	}
}