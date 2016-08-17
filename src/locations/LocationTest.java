import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

	@Test
	public void testIsNotContains() {
		Location loc1 = new Location(0,0,1);
		Location loc2 = new Location(0,0,2);
		
		Contains contains = loc1.isContains(loc2);
		
		Assert.assertEquals(contains.NOT_CONTAINS, contains);
	}
	
	@Test
	public void testIsContains() {
		Location loc1 = new Location(0,0,1);
		Location loc2 = new Location(0,0,2);
		
		Contains contains = loc2.isContains(loc1);
		
		Assert.assertEquals(contains.CONTAINS, contains);
		
	}
}
