package geoLocations;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import abstractUtils.CSVScanner;
import dataModels.PairedData;
import geoHashUtils.BitSetBuilder;
import geoHashUtils.GeoHash;
import haversineUtils.DistanceBasedUtilty;

public class LocationCalculator {

	private static final Logger logger = (Logger) LogManager.getLogger(LocationCalculator.class.getName());

	public String processLoc(double lat, double lon, int rad) {
		GeoHash geo = null;
		BitSetBuilder bitset = null;

		bitset = new BitSetBuilder(lat, lon, rad);
		boolean[] bits = bitset.createBitset();
		geo = new GeoHash(bits);
		geo.exchangeValue();

		return geo.getGeoHash();
	}

	public ArrayList<PairedData> calculate(CSVScanner passedScanner, PairedData userInput) {

		ArrayList<PairedData> arrayFilledWithPairedData = new ArrayList<PairedData>();
		DistanceBasedUtilty helper;
		
		double lat1 = userInput.getLoc().getLatitude();
		double lon1 = userInput.getLoc().getLongitude();
		int rad1 = userInput.getLoc().getRadius();

		userInput.setGeoHash(processLoc (lat1, lon1, rad1) );

		for (int i = 0; i < passedScanner.getContainer().size(); i++) {
			logger.info(":::::::::: NEW LOCATION ::::::::::");
			logger.info("SUPER is still ->  Lat: " + lat1 + " Lon: " + lon1 + " with " + rad1 + " meter radius.");
			
			PairedData fileInput = new PairedData( passedScanner.getIteratedContainer(i) );
			double lat2 = fileInput.getLoc().getLatitude();
			double lon2 = fileInput.getLoc().getLongitude();
			int rad2 = fileInput.getLoc().getRadius();
			System.out.println("fika");
			logger.info(
					"Got the following data ->  Lat: " + lat2 + " Lon: " + lon2 + " with " + rad2 + " meter radius");

			fileInput.setGeoHash( processLoc(lat2, lon2, rad2) );

			helper = new DistanceBasedUtilty( userInput.getLoc() );
			userInput.setContains(helper.isContains(fileInput.getLoc()));
			userInput.setDistance(helper.distanceTo(fileInput.getLoc()));
			
			arrayFilledWithPairedData.add(new PairedData(fileInput.getLoc(), fileInput.getGeoHash(), userInput.getContains(),
					userInput.getDistance()));

			logger.info("SUPER is " + userInput.getContains() + " that location.");
			logger.info("Distance between SUPER and that location (in kilometers): " + userInput.getDistance());
		}
		return arrayFilledWithPairedData;
	}
}
