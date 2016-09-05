package hu.benyuss.geohash.geoLocations;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import hu.benyuss.geohash.abstractUtils.CSVScanner;
import hu.benyuss.geohash.dataModels.Location;
import hu.benyuss.geohash.dataModels.PairedData;
import hu.benyuss.geohash.geoHashUtils.BitSetBuilder;
import hu.benyuss.geohash.geoHashUtils.GeoHash;
import hu.benyuss.geohash.haversineUtils.DistanceBasedUtilty;
import hu.benyuss.geohash.webserver.database.LocationDB;

public class LocationCalculator {

	private static final Logger logger = (Logger) LogManager.getLogger(LocationCalculator.class.getName());

	public String generateGeohash(double lat, double lon, int rad) {
		GeoHash geo;
		BitSetBuilder bitset;

		bitset = new BitSetBuilder(lat, lon, rad);
		boolean[] bits = bitset.createBitset();
		geo = new GeoHash(bits);
		geo.exchangeValue();

		return geo.getGeoHash();
	}

	public ArrayList<PairedData> calculate(ArrayList<LocationDB> data, LocationDB loc) {

		DistanceBasedUtilty helper;
		PairedData userInput = new PairedData(); //the one which is selected by user.

		ArrayList<PairedData> arrayWithFileInput = new ArrayList<PairedData>(); //all except that.

		userInput.setLoc(new Location(loc.getLatitude(), loc.getLongitude(), loc.getRadius())); // fill from DB.
		userInput.setGeoHash(loc.getGeohash());

		for (LocationDB temploc : data) {
			logger.info(":::::::::: NEW LOCATION ::::::::::");
			logger.info("SUPER is still -> Lat: " + userInput.getLoc().getLatitude() + " Lon: " + userInput.getLoc().getLongitude() + " with "
					+ userInput.getLoc().getRadius() + " meter radius.");
			
			PairedData fileInput = new PairedData(); //filled from DB
			fileInput.setLoc(new Location(temploc.getLatitude(), temploc.getLongitude(), temploc.getRadius()));
			fileInput.setGeoHash(temploc.getGeohash());

			helper = new DistanceBasedUtilty(userInput.getLoc());
			userInput.setContains(helper.isContains(fileInput.getLoc()));
			userInput.setDistance(helper.distanceTo(fileInput.getLoc()));
			
			arrayWithFileInput.add(new PairedData(fileInput.getLoc(), fileInput.getGeoHash(), userInput.getContains(),
					userInput.getDistance()));
			
			logger.info("SUPER is " + userInput.getContains() + " that location.");
			logger.info("Distance between SUPER and that location (in kilometers): " + userInput.getDistance());
		}

		return arrayWithFileInput;
	}

	public ArrayList<PairedData> fillDBWithCSV(CSVScanner passedScanner) { 
		// parse csv file and return it to the sql table

		ArrayList<PairedData> arrayFilledWithPairedData = new ArrayList<PairedData>(); //it will store that parsed data.
		
		for (int i = 0; i < passedScanner.getContainer().size(); i++) {

			PairedData fileInput = new PairedData(passedScanner.getIteratedContainer(i));
			Double lat1 = fileInput.getLoc().getLatitude();
			Double lon1 = fileInput.getLoc().getLongitude();
			int rad1 = fileInput.getLoc().getRadius();

			logger.info(
					"Got the following data ->  Lat: " + lat1 + " Lon: " + lon1 + " with " + rad1 + " meter radius");

			fileInput.setGeoHash(generateGeohash(lat1, lon1, rad1));

			arrayFilledWithPairedData.add(new PairedData(fileInput.getLoc(), fileInput.getGeoHash()));
		}
		return arrayFilledWithPairedData;
	}
}
