public class BitSetBuilder extends Location {

	public BitSetBuilder(double lat, double lon, int radius) {
		super(lat, lon, radius);
	}

	// lon - hosszúság - függőleges - vertical (-180 -> +180) páros
	// lat - szélesség - vízszintes - horizontal [<--->] (-90 -> +90) páratlan 
	
	private static final double MAXLon = 180;
	private static final double MAXLat = 90;
	private double maxLon = 180;
	private double minLon = -180;
	private double maxLat = 90;
	private double minLat = -90;
	private static final int arrayLength = 40;

//	private BitSet bits;
	private boolean bits[];

	public static int getArrayLength() {
		return arrayLength;
	}

	private final void checkHorizontal(int bitSetIndex) {
		if (((maxLat + minLat) / 2) <= lat) {
//			bits.set(bitSetIndex, true);
			bits[bitSetIndex] = true;
		} else {
//			bits.set(bitSetIndex, false);
			bits[bitSetIndex] = false;
		}
	}

	private final void checkVertical(int bitSetIndex) {
		if (((maxLon + minLon) / 2) <= lon) {
//			bits.set(bitSetIndex, true);
			bits[bitSetIndex] = true;
		} else {
//			bits.set(bitSetIndex, false);
			bits[bitSetIndex] = false;
		}
	}

	private final void CurrentLonCalc(int x, int bitSetIndex) {
		// KOMPLETT BAL OLDAL
//		if (bits.get(bitSetIndex) == true) {
		if (bits[bitSetIndex] == true) {
			minLon += MAXLon / x;
		} else {
			maxLon -= MAXLon / x;
		}
		// VÉGE
	}

	private final void CurrentLatCalc(int x, int bitSetIndex) {
		// KOMPLETT BAL OLDAL
//		if (bits.get(bitSetIndex) == true) {
		if (bits[bitSetIndex] == true) {
			minLat += MAXLat / x;
		} else {
			maxLat -= MAXLat / x;
		}
		// VÉGE
	}
	 

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
		System.out.println(sb.toString());
		return sb.toString();
	}

	public boolean[] createBitset() {
		int x = 1;
		int bitSetIndex = 0;
		bits = new boolean[arrayLength];
//		bits = new BitSet(arrayLength);
		for (int i = 0; i < arrayLength/2; ++i) {
			
			checkVertical(bitSetIndex);
			CurrentLonCalc(x, bitSetIndex);
			bitSetIndex++;
			checkHorizontal(bitSetIndex);
			CurrentLatCalc(x, bitSetIndex);
			bitSetIndex++;
			x = x * 2;
		}
		bitSetToString(bitSetIndex, bits);
		return bits;
		// return bitSetToString(arrayLength);
	}
}
