package locations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

class GeoHash {
	// It will parse the bitset made by BitSetBuilder class. Look at there for
	// more info.

	static final Logger logger = (Logger) LogManager.getLogger(GeoHash.class);

	boolean[] bitSet;
	private String geoHash; // Will store the final hash.

	public GeoHash(boolean[] bits) { // Constructor should get the bitSet to
		// start parsing process.
		logger.debug(bits);
		this.bitSet = bits;
	}

	public String getGeoHash() { // Just a getter.
		logger.info("Geohash is: " + geoHash);
		return geoHash;
	}

	static class Base32 { // Character table based on Geohash Wiki.
		private static String stringBase = "0123456789bcdefghjkmnpqrstuvwxyz";
		private static char Base32array[] = stringBase.toCharArray(); // to get
		// them
		// by
		// value.
		// Value
		// =
		// index.

		public static char getBase(int x) { // It's a simple getter. Based on
			// the fact Value = index.
			logger.debug("getBase method got a(n): " + x + " and that's " + Base32array[x]);
			return Base32array[x];
		}
	}

	// Used in tests.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoHash other = (GeoHash) obj;
		if (geoHash == null) {
			if (other.geoHash != null)
				return false;
		} else if (!geoHash.equals(other.geoHash))
			return false;
		return true;
	}

	// This is the method which do the job.
	public void exchangeValue() {
		double values[] = new double[5]; // Because 5 bits means a geohash
											// character. Look at Geohash wiki
											// for character table. (Or Base32
											// class.) It will be cleaned in
											// every round(char by char).
		int it = 0; // iterator for the same reason. (it also used to get pow of
					// 2. Bits are like normal binary, has a value. Current
					// character is calculated by that.
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < BitSetBuilder.getArrayLength();) {
			while (it < 5) { // if it's 5 it should be zeroed because pow of 2
								// can not be raised more. Puts a zero
				if (bitSet[i] == true) {
					values[it] = Math.pow(2, 4 - it); // adds pow of 2 value to
														// the current index
					logger.trace("true \t" + values[it]);
				} else {
					values[it] = 0; // adds 0 value to the current index
					logger.trace("false \t" + values[it]);
				}
				it++;
				i++;
			}
			int finalValue = 0;

			for (double asd : values) { // now it stores 5 values which means a
										// character. If we add them we can get
										// the finalValue and a character.
				finalValue += asd;
			}
			it = 0;
			char base = Base32.getBase(finalValue);
			sb.append(base); // We have added a character to the string.
		}
		geoHash = sb.toString(); // Our StringBuilder now stores the whole
									// Geohash so it's time to pass it.
	}
}
