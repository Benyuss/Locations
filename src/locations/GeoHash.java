package locations;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

class Geohash {
	
	BitSet bitSet;
	
	public Geohash (BitSet bits) {
		this.bitSet = bits;
	}
	
	private String geoHash;
	
	private enum Base32 {
		A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7), I(8), J(9), K(10), L(11),
		M(12), N(13), O(14), P(15), Q(16), R(17), S(18), T(19), U(20), V(21), W(22),
		X(23), Y(24), Z(25), _2(26), _3(27), _4(28), _5(29), _6(30), _7(31); 
		
		private static final Map<Integer, Base32> terkep = new HashMap<Integer, Base32>();
		  static {
		    for (Base32 base : values()) {
		      terkep.put(base.getValue(), base);
		    }
		  }
		
		private int value;
		
		private Base32(int value) {
		      this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public static Base32 getByValue(int value) {
		    return terkep.get(value);
		  }

		  public String toString() {
		    return name();
		  }			
	}
	
	public void exchangeValue () {
		double values [] = new double[5];
		String hash = null;
		int it = 0;
		for (int i = 0; i < BitSetBuilder.getArrayLength();) {
			while (it != 5) {
				if (bitSet.get(i) == true) {
					values[it] = Math.pow(2, it);
				}
				else {
					values[it] = 0;
				}
				it++;
				i++;
			}
			int finalValue = 0;
			
			for (double asd: values) {
				finalValue += asd;
			}
			it = 0;
			Base32 base = Base32.getByValue(finalValue);
			hash = String.valueOf(base);
			geoHash = hash;
			System.out.print(geoHash);
		}
	}
	
	public String getGeoHash() {
		return geoHash;
	}
}
