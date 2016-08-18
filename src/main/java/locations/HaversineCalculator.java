package locations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

public final class HaversineCalculator {
//Calculates the Haversine Formula. Look at https://en.wikipedia.org/wiki/Haversine_formula for more info.
	
	private static Logger logger = null;
	static {
		
		try {
			InitLogger.initialize();
		} catch (FileNotFoundException e) {
			logger.log(Level.ERROR, "Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.ERROR, "Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		}
		
		logger = InitLogger.logger[0];
	}
	
	public static final double R = 6371.3;
	
	public double haversineValue (double lat1, double lon1, double lat2, double lon2) {
		
//		logger.log(Level.DEBUG, "Haversine calculator got the following data -> " +
//					"Lat of first: " + lat1 + " Lon of first: " + lon1 +
//					" Lat of second: " + lat2 + " Lon of second: " + lon2);
		
		double latDistance = Math.toRadians( lat2 - lat1 );
		double lonDistance = Math.toRadians( lon2 - lon1 );

		double a = Math.pow(Math.sin(latDistance / 2d) , 2) +
				   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
				   Math.pow(Math.sin(lonDistance / 2d) , 2);

		double c = 2d * Math.atan2(Math.sqrt(a), Math.sqrt(1d-a));
		
//		logger.log(Level.DEBUG, "Distance between them is: " + R * c + " kilometers.");
		return R * c;
	}
}