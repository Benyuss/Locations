package locations;

import java.util.BitSet;

public class BitSetBuilder extends Location {

	public BitSetBuilder(double lat, double lon, int radius) {
		super(lat, lon, radius);
	}

	// lat függőleges - szélesség- vertical || lon vízszintes - hosszúság -
	// horizontal
	private static final double MAXLon = 180;
	private static final double MAXLat = 90;
	private double maxLon = 180;
	private double minLon = -180;
	private double maxLat = 90;
	private double minLat = -90;
	private static final int arrayLength = 40;
	
	private BitSet bits;
	
	public static int getArrayLength() {
		return arrayLength;
	}

	private final void checkHorizontal(int bitSetIndex) {
		if ((maxLon + minLon) <= lon) {
			bits.set(bitSetIndex, true);
		} else {
			bits.set(bitSetIndex, false);
		}
	}

	private final void checkVertical(int bitSetIndex) {
		if ((maxLat + minLat) <= lat) {
			bits.set(bitSetIndex, true);
		} else {
			bits.set(bitSetIndex, false);
		}
	}

	private final void CurrentLonCalc(int x, int bitSetIndex) {
		// KOMPLETT BAL OLDAL
		if (bits.get(bitSetIndex) == true) {
			minLon += MAXLon / x;
		} else {
			maxLon -= MAXLon / x;
		}
		// VÉGE
	}

	private final void CurrentLatCalc(int x, int bitSetIndex) {
		// KOMPLETT BAL OLDAL
		if (bits.get(bitSetIndex) == true) {
			minLat += MAXLat / x;
		} else {
			maxLat -= MAXLat / x;
		}
		// VÉGE
	}
	
	public String bitSetToString(int arrayLength, BitSet bits) {

		StringBuilder sb = new StringBuilder();

		int counter = 0;
		for (int i = 0; i < arrayLength; i++) {
			if (counter == 5) {
				sb.append(' ');
				counter = 0;
			}
			if (bits.get(i) == false) {
				sb.append('0');
			} else {
				sb.append('1');
			}
			counter++;
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public BitSet createBitset() {
		int x = 1;
		int bitSetIndex = 0;
		bits = new BitSet(arrayLength);
		for (int i = 0; i < arrayLength; ++i) {
			checkHorizontal(bitSetIndex);
			CurrentLonCalc(x, bitSetIndex);
			checkVertical(bitSetIndex);
			CurrentLatCalc(x, bitSetIndex);

			x = x * 2;
			bitSetIndex++;
		}
		bitSetToString(bitSetIndex, bits);
		return bits;
		//return bitSetToString(arrayLength);
	}	
}
