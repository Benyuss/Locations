package locations;

import static org.junit.Assert.*;
import java.util.BitSet;
import org.junit.Test;

public class GeoHashTest {

	@Test
	public void test() {
		BitSetBuilder test = new BitSetBuilder(48.104564, 20.800041, 3);
		BitSet bits = test.createBitset();
		Geohash geo = new Geohash(bits);
	//	assertEquals( , geo);
	}

}
