package locations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

public class BitSetBuilder extends Location {
	// Creates a bitset based on Geohash calculating algorithm. It will be
	// parsed and converted into Geohash value.
	// Further info -> http://www.bigfastblog.com/geohash-intro
	// Wiki page -> https://en.wikipedia.org/wiki/Geohash

	private static Logger logger = null;

	static {

		try {
			InitLogger.initialize();
		} catch (FileNotFoundException e) {
			logger.log(Level.ERROR,
					"Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.ERROR,
					"Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		}

		logger = InitLogger.logger[0];
	}

	public BitSetBuilder(double lat, double lon, int radius) {
		super(lat, lon, radius);
	}

	// lon - vertical (-180 -> +180) every even round
	// lat - horizontal [<--->] (-90 -> +90) every odd round

	private static final double MAXLon = 180;
	private static final double MAXLat = 90;
	private double maxLon = 180;
	private double minLon = -180;
	private double maxLat = 90;
	private double minLat = -90;
	private static final int arrayLength = 40; // length / 5 = x ; Geohash will
												// be x characters long.

	private boolean bits[];

	public static int getArrayLength() {
		return arrayLength;
	}

	private final void checkHorizontal(int bitSetIndex) {
		if (((maxLat + minLat) / 2) <= lat) {
			bits[bitSetIndex] = true;
		} else {
			bits[bitSetIndex] = false;
		}
	}

	private final void checkVertical(int bitSetIndex) {
		if (((maxLon + minLon) / 2) <= lon) {
			bits[bitSetIndex] = true;
		} else {
			bits[bitSetIndex] = false;
		}
	}

	private final void CurrentLonCalc(int x, int bitSetIndex) {
		// Complete left side
		if (bits[bitSetIndex] == true) {
			minLon += MAXLon / x;
			logger.log(Level.INFO, "minLon : " + minLon);
		} else {
			maxLon -= MAXLon / x;
			logger.log(Level.INFO, "maxLon : " + maxLon);
		}
	}

	private final void CurrentLatCalc(int x, int bitSetIndex) {
		// KOMPLETT BAL OLDAL
		// if (bits.get(bitSetIndex) == true) {
		if (bits[bitSetIndex] == true) {
			minLat += MAXLat / x;
			logger.log(Level.INFO, "minLat : " + minLat);
		} else {
			maxLat -= MAXLat / x;
			logger.log(Level.INFO, "maxLat : " + maxLat);
		}
		// VÉGE
	}

	// toString will look like this: 11001 10101 01010 10001 .... (5chars +
	// space)
	public String bitSetToString(int arrayLength, boolean bits[]) {

		StringBuilder sb = new StringBuilder();

		int counter = 0;
		for (int i = 0; i < (arrayLength);) {
			if (counter == 5) {
				sb.append(' ');
				counter = 0;
			}
			if (bits[i] == false) {
				sb.append('0');
				i++;
			} else {
				sb.append('1');
				i++;
			}
			counter++;
		}
		String temp = sb.toString(); // 3x call of toString is expensive.
										// (return, log, sysout if needed).
		logger.log(Level.DEBUG, "bitset as string :: " + temp);
		return temp;
	}

	// Bread and butter. This method do the job.
	public boolean[] createBitset() {
		int x = 1;
		int bitSetIndex = 0;
		bits = new boolean[arrayLength];
		// bits = new BitSet(arrayLength);
		for (int i = 0; i < arrayLength / 2; ++i) {

			checkVertical(bitSetIndex);
			CurrentLonCalc(x, bitSetIndex);
			bitSetIndex++;
			checkHorizontal(bitSetIndex);
			CurrentLatCalc(x, bitSetIndex);
			bitSetIndex++;
			x = x * 2;
		}
		bitSetToString(bitSetIndex, bits);
		// logger.log(Level.DEBUG, bits);
		return bits;
	}
}
