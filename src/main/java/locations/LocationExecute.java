package locations;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LocationExecute {

	static final Logger logger = (Logger) LogManager.getLogger(LocationExecute.class.getName());

	public static ArrayList<Tuple> tempArray = new ArrayList<Tuple>();;
	private static StringBuilder tempString = new StringBuilder();

	public static String getTempString() {
		return tempString.toString();
	}

	public static ArrayList<Tuple> getTempArray() {
		return tempArray;
	}
	
	public static String processLoc (double lat, double lon, int rad) {
		GeoHash geo = null;
		BitSetBuilder bitset = null;
		
		bitset = new BitSetBuilder(lat, lon, rad);
		boolean[] bits = bitset.createBitset();
		geo = new GeoHash(bits);
		geo.exchangeValue();
		
		return geo.getGeoHash();
	}
	
	public static void calculate(CSVScanner passedScanner, Tuple userData) {
		
		String currentHash;
		DistanceHelp helper;
		double lat1 = userData.getFirstCoordinate();
		double lon1 = userData.getSecondCoordinate();
		int rad1 = userData.getRadius();
		
		processLoc(lat1, lon1, rad1);

		for (int i = 0; i < passedScanner.getContainer().size(); i++) {
			logger.info( ":::::::::: NEW LOCATION ::::::::::");
			logger.info(
					"SUPER is still ->  Lat: " + lat1 + " Lon: " + lon1 + " with " + rad1 + " meter radius.");

			Tuple pair2 = passedScanner.getIteratedContainer(i);
			double lat2 = pair2.getFirstCoordinate();
			double lon2 = pair2.getSecondCoordinate();
			int rad2 = pair2.getRadius();
			logger.info(
					"Got the following data ->  Lat: " + lat2 + " Lon: " + lon2 + " with " + rad2 + " meter radius");
			Location loc1 = new Location(lat1, lon1, rad1);
			Location loc2 = new Location(lat2, lon2, rad2);

			currentHash = processLoc(lat2, lon2, rad2);
			
			helper = new DistanceHelp(loc1);
			
			tempArray.add( new Tuple (lat2, lon2, rad2, currentHash, helper.isContains(loc2), helper.distanceTo(loc2) )
					);
			
			logger.info( "SUPER is " + helper.isContains(loc2) + " that location.");
			logger.info(
					"Distance between SUPER and that location (in kilometers): " + helper.distanceTo(loc2));
		}
	}
}
