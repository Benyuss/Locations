class Geohash {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geoHash == null) ? 0 : geoHash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geohash other = (Geohash) obj;
		if (geoHash == null) {
			if (other.geoHash != null)
				return false;
		} else if (!geoHash.equals(other.geoHash))
			return false;
		return true;
	}

	boolean[] bitSet;
	
	public Geohash (boolean[] bits) {
		this.bitSet = bits;
	}
	
	private String geoHash;
	
	static class Base32 {
		private static String stringBase = "0123456789bcdefghjkmnpqrstuvwxyz";
		private static char Base32array[] = stringBase.toCharArray();
		
		public static char getBase (int x) {
			return Base32array[x];
		}
	
	}
	
	public void exchangeValue () {
		double values [] = new double[5];
		int it = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < BitSetBuilder.getArrayLength() ;) {
			while (it < 5) {
				if (bitSet[i] == true) {
					values[it] = Math.pow(2, 4-it);
					System.out.println("true\t" + values[it]);
				}
				else {
					values[it] = 0;
					System.out.println("false\t" + values[it]);
				}
				it++;
				i++;//u2wc9m4j u2wc8uz9
			}
			int finalValue = 0;
			
			for (double asd: values) {
				finalValue += asd;
			}
			it = 0;
			char base = Base32.getBase(finalValue);
			sb.append(base);
		}
		geoHash = sb.toString();
		System.out.println(geoHash);
	}
	
	public String getGeoHash() {
		return geoHash;
	}
}
