package locations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import geoHashUtils.BitSetBuilder;
import geoHashUtils.GeoHash;

public class GeoHashTest {
	@Test
	public void test() {
		BitSetBuilder test = new BitSetBuilder(48.102501, 20.785504, 3);
		boolean[] bits = test.createBitset();
		GeoHash geo = new GeoHash(bits);

		// 11010 00010 11100 01011 01000 11010 11111 01001
		boolean[] expected = { true, true, false, true, false, false, false, false, true, false, true, true, true,
				false, false, false, true, false, true, true, false, true, false, false, false, true, true, false, true,
				false, true, true, true, true, true, false, true, false, false, true };
		GeoHash geoexp = new GeoHash(expected);
		assertEquals(geoexp, geo);
	}

}
