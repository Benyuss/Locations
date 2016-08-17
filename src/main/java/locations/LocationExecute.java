package locations;

import java.util.Scanner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.*;

import java.util.ArrayList;
import java.util.BitSet;


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
			tuple.setFirstCoord(Double.parseDouble(coordinates[0]));
			tuple.setSecondCoord(Double.parseDouble(coordinates[1]));
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

final class Tuple {
//Pairs 2 coordinates with a radius. Use it to keep the data together from csv files. 
	
    @Override
	public String toString() {
		return "Tuple [firstCoordinate=" + firstCoordinate + ", secondCoordinate=" + secondCoordinate + ", radius="
				+ radius + "]";
	}

	private Double firstCoordinate;
    private Double secondCoordinate;
    private int radius;

	public Double getFirstCoord() {
    	return firstCoordinate;	
    }
    
    public Double getSecondCoord() {
    	return secondCoordinate;
    }
    
    public void setFirstCoord( double cord ) {
    	firstCoordinate = cord;
    }
    
    public void setSecondCoord( double cord ) {
    	secondCoordinate = cord;
    }

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}

class LocationExecute {
	
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
	
	public static void calculate() {
		
		try {
			CSVscanner.scan();
		}catch (Exception e) {};

		
		GetData data = new GetData();
		double lat1 = data.setLat1();
		double lon1 = data.setLon1();
		int rad1 = data.setRad1();
		//double lat2 = data.setLat2();
		//double lon2 = data.setLon2();
//		logger.log(Level.DEBUG, "firstlog");

		for(int i = 0; i < CSVscanner.getLastIndex(); i++) {
			
			//Pair pair = CSVscanner.container.get(i);
			//double lat1 = pair.getFirstCoord();
			//double lon1 = pair.getSecondCoord();

			Tuple pair2 = CSVscanner.container.get(i);
			double lat2 = pair2.getFirstCoord();
			double lon2 = pair2.getSecondCoord();
			int rad2 = pair2.getRadius();
			logger.log(Level.DEBUG, "Got the following data ->  Lat: " + lat2 + "\tLon: " + lon2);
			Location loc1 = new Location(lat1, lon1, rad1);
			Location loc2 = new Location(lat2, lon2, rad2);
			
//			logger.log(Level.DEBUG, loc1.isContains(loc2));
			
			geo1 = new BitSetBuilder(lat2,lon2,rad2);
			boolean[] bits = geo1.createBitset();
			geo = new Geohash(bits);
			geo.exchangeValue();
			nabstring.append("Hash of: " + lat2 + "  (lat)\t" + lon2 +"  (lon)\t is :: " 
							+ geo.getGeoHash() + "<br>");
//			logger.log(Level.DEBUG, geo.getGeoHash());
			//Geohash geo2 = new Geohash(lat2, lon2, rad2);
				//geo2.createBitset();
		}
		//CSVscanner.testScanner();
	}
}
