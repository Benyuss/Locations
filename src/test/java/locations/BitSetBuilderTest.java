package locations;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitSetBuilderTest {

	@Test
	public void testprocessBitset() {
		BitSetBuilder test = new BitSetBuilder(48.102501, 20.785504, 3);
		boolean[] notActual = test.createBitset(); 
		String actual = test.bitSetToString(40, notActual);
		
		String expected = "11010 00010 11100 01011 01000 11010 11111 01001";
		assertEquals(expected, actual);

	}

}
