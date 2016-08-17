import static org.junit.Assert.*;

import org.junit.Test;

public class HaversineCalculatorTest {

	@Test
	public void testHaversineValue() {
		HaversineCalculator hav = new HaversineCalculator();
		double result = hav.haversineValue(1, 2, 3, 4);
		assertEquals("debrecen miskolc távolság", 5.23, result, .05);
	}
}
