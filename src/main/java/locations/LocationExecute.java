package locations;

import java.util.Scanner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.*;

import java.util.ArrayList;

class GetData {
//Can get user input from console. The 2 inactive methods "setLat2 and setLon2" are used to compare 2 locations given by user.
//You can reactivate them but if you do, CSV input will make no sense.
	
	Scanner scanner = new Scanner(System.in);

	public int setRad1() {
   		//System.out.println("Enter the radius: ");
   		return 6; //scanner.nextInt();
	}

	public double setLat1() {
    	//System.out.println("Enter the first latitude: ");
    	return 48.104564; //scanner.nextDouble(); 
    }

    public double setLon1() {
	    //System.out.println("Enter the first longitude: ");
	    return 20.800041; //scanner.nextDouble();
	} 
    /*
    public double setLat2() {	
 		System.out.println("Enter the second latitude: ");
 		double lat2 = scanner.nextDouble();
    	return lat2;
    }

	public double setLon2() {
	    System.out.println("Enter the second longitude: ");
	    double lon2 = scanner.nextDouble();
	    return lon2;
	}
	
	public int setRad2() {
   		System.out.println("Enter the radius: ");
   		int rad = scanner.nextInt();
   		return rad;
	}
	*/ 
}

final class CSVscanner {
//Scans CSV files until their last line. Saves data in Tuples (LOOk AT -> class Tuple- line 132) to ArrayList. 
//You can test the class with CSVscanner.testScanner(); 
	
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
	
	static ArrayList<Tuple> container;
	private static int lastIndex;
	
	public static int getLastIndex() {
		return lastIndex;
	}

    public static void scan() throws Exception {
  	
		CSVReader scanner = new CSVReader(new FileReader("coordinates.csv"));
		String [] coordinates;
		container = new ArrayList<Tuple>();
		
		while ((coordinates = scanner.readNext()) != null) {
			Tuple tuple = new Tuple();
			tuple.setFirstCoordinate(Double.parseDouble(coordinates[0]));
			tuple.setSecondCoordinate(Double.parseDouble(coordinates[1]));
			tuple.setRadius(Integer.parseInt(coordinates[2]));
			container.add(tuple);
			lastIndex = container.size();
		}
		scanner.close();
    }
    
    public static void testScanner() {
		for (Tuple asd : container) {
			System.out.println(asd);
			logger.log(Level.DEBUG, "ScannerTest: " + asd);
		}
    }	
}

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

	private static Geohash geo = null;
	private static BitSetBuilder geo1 = null;
	private static ArrayList<Tuple> tupleList;
	
	public static ArrayList<Tuple> getTupleList() {
		return tupleList;
	}

	public static void calculate() {
		
		tupleList = new ArrayList<Tuple>();
		
		try {
			CSVscanner.scan();
		}catch (Exception e) {};
		
		GetData data = new GetData();
		double lat1 = data.setLat1();
		double lon1 = data.setLon1();
		int rad1 = data.setRad1();
		geo1 = new BitSetBuilder(lat1,lon1,rad1);
		boolean[] bits1 = geo1.createBitset();
		geo = new Geohash(bits1);
		geo.exchangeValue();
		tupleList.add(new Tuple(lat1, lon1, rad1, geo.getGeoHash()));
		
		logger.log(Level.DEBUG, "Got the following data (super)->  Lat: " + lat1 + " Lon: " + lon1);
		
		for (int i = 0; i < CSVscanner.getLastIndex(); i++) {
			int k = 1;
			logger.log(Level.INFO, ":::::::::: NEW TREE ::::::::::");
			logger.log(Level.INFO, "SUPER is still ->  Lat: " + lat1 + " Lon: " + lon1+ " with " + rad1 + " meter radius");
			Tuple pair2 = CSVscanner.container.get(i);
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
			
			logger.log(Level.DEBUG, "SUPER is " + loc1.isContains(loc2) + " that location." );
			logger.log(Level.DEBUG, "Distance between SUPER and that location (in kilometers): " + loc1.distanceTo(loc2) );
		}
	}
}
