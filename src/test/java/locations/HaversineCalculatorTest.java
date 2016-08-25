package locations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import haversineUtils.HaversineCalculator;

public class HaversineCalculatorTest {

	@Test
	public void testHaversineValue() {
		HaversineCalculator hav = new HaversineCalculator();
		double result = hav.haversineValue(47.551505, 21.609753, 47.558030, 21.604825);
		assertEquals(1, result, .5);
	}
}
