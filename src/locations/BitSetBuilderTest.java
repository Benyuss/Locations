package locations;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Test;

public class BitSetBuilderTest {

	@Test
	public void testprocessBitset() {
		BitSetBuilder test = new BitSetBuilder(48.104564, 20.800041, 3);
		BitSet notActual = test.createBitset(); 
		String lofasz = test.bitSetToString(40, notActual);
		String result = "10100 01000 11010 01100 10110 00001 10010 00000";
		assertEquals(result, lofasz);

	}

}
