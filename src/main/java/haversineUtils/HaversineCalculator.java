package haversineUtils;

public final class HaversineCalculator {
	// Calculates the Haversine Formula. Look at
	// https://en.wikipedia.org/wiki/Haversine_formula for more info.

	public static final double R = 6371.3;

	public double haversineValue(double lat1, double lon1, double lat2, double lon2) {

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);

		double a = Math.pow(Math.sin(latDistance / 2d), 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(lonDistance / 2d), 2);

		double c = 2d * Math.atan2(Math.sqrt(a), Math.sqrt(1d - a));

		return R * c;
	}
}