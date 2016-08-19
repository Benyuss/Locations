package locations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

//import java.util.Scanner;

public final class GetData {
	//Can get user input from console. The 2 inactive methods "setLat2 and setLon2" are used to compare 2 locations given by user.
	//You can reactivate them but if you do, CSV input will make no sense.
		
//		Scanner scanner = new Scanner(System.in);
	
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
	
	private GetData () {
		
	}
		private static int rad1;
		private static double lat1;
		private static double lon1;
		
		public static int getRad1() {
			return rad1;
		}
		
		public static double getLat1() {
			return lat1;
		}
		
		public static double getLon1() {
			return lon1;
		}
		
		public static void setRad1(int rad) {
	   		//System.out.println("Enter the radius: ");
			if (rad > 0) {
				rad1 = rad;
			}
			else {
				logger.log(Level.DEBUG, "GetData class got an illegal rad value: {}", rad);
				throw new IllegalArgumentException("GetData class got an illegal rad value");
			}
			
	   		//6; //scanner.nextInt();
		}

		public static void setLat1(double lat) {
	    	//System.out.println("Enter the first latitude: ");
			if (lat > -90 && lat < 90) {
				lat1 = lat;
			}
			else {
				logger.log(Level.DEBUG, "GetData class got an illegal lat value: {}", lat);
				throw new IllegalArgumentException("GetData class got an illegal lat value");
			}
				
	    	 //48.104564; //scanner.nextDouble(); 
	    }

	    public static void setLon1(double lon) {
		    //System.out.println("Enter the first longitude: ");
	    	if (lon > -180 && lon < 180) {
	    		lon1 = lon;
	    	}
	    	else {
	    		logger.log(Level.DEBUG, "GetData class got an illegal lon value: {}", lon);
				throw new IllegalArgumentException("GetData class got an illegal lon value");
			}
	    	
		    //return lon; //20.800041; //scanner.nextDouble();
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