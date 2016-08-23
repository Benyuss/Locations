package locations;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.*;

import java.util.ArrayList;

public class LocationExecute {
	
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
	
	private static StringBuilder nabstring = new StringBuilder();
	
	public static String getNabstring() {
		return nabstring.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static final ArrayList<Tuple>[] tempArray = new ArrayList[1];
	
	
	public static ArrayList<Tuple>[] getTempArray() {
		return tempArray;
	}

	public static void calculate(CSVScanner passedScanner) {
		
		Geohash geo = null;
		BitSetBuilder geo1 = null;
		ArrayList<Tuple> tupleList = new ArrayList<Tuple>();
		CSVScanner scanner = passedScanner;
		
		double lat1 = GetData.getLat1();
		double lon1 = GetData.getLon1();
		int rad1 = GetData.getRad1();
		geo1 = new BitSetBuilder(lat1,lon1,rad1);
		boolean[] bits1 = geo1.createBitset();
		geo = new Geohash(bits1);
		geo.exchangeValue();
		tupleList.add(new Tuple(lat1, lon1, rad1, geo.getGeoHash()));
		
		logger.log(Level.DEBUG, "Got the following data (super)->  Lat: " + lat1 + " Lon: " + lon1);
		
		for (int i = 0; i < scanner.getContainer().size(); i++) {
			int k = 1;
			logger.log(Level.INFO, ":::::::::: NEW TREE ::::::::::");
			logger.log(Level.INFO, "SUPER is still ->  Lat: " + lat1 + " Lon: " + lon1+ " with " + rad1 + " meter radius");
			
			Tuple pair2 = scanner.getIteratedContainer(i);
			double lat2 = pair2.getFirstCoordinate();
			double lon2 = pair2.getSecondCoordinate();
			int rad2 = pair2.getRadius();
			logger.log(Level.DEBUG, "Got the following data ->  Lat: " + lat2 + " Lon: " + lon2 + " with " + rad2 + " meter radius");
			Location loc1 = new Location(lat1, lon1, rad1);
			Location loc2 = new Location(lat2, lon2, rad2);
			
			geo1 = new BitSetBuilder(lat2,lon2,rad2);
			boolean[] bits2 = geo1.createBitset ();
			geo = new Geohash(bits2);
			geo.exchangeValue();
			tupleList.add(new Tuple(lat2, lon2, rad2, geo.getGeoHash(), loc1.isContains(loc2), loc1.distanceTo(loc2)));
			
			tempArray[0] = tupleList;
			
			logger.log(Level.DEBUG, "SUPER is " + loc1.isContains(loc2) + " that location." );
			logger.log(Level.DEBUG, "Distance between SUPER and that location (in kilometers): " + loc1.distanceTo(loc2) );
		}
	}
}
