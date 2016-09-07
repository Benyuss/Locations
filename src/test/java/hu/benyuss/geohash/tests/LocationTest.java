package hu.benyuss.geohash.tests;

import org.junit.Assert;
import org.junit.Test;

import hu.benyuss.geohash.dataModels.Location;
import hu.benyuss.geohash.haversineUtils.Contains;
import hu.benyuss.geohash.haversineUtils.DistanceBasedUtilty;

public class LocationTest {

	@Test
	public void testIsSemiContains() {
		Location loc1 = new Location(0.0, 0.0, 1);
		Location loc2 = new Location(0.0, 0.0, 2);
		DistanceBasedUtilty helper = new DistanceBasedUtilty(loc1);
		Contains contains = helper.isContains(loc2);

		Assert.assertEquals(Contains.SEMI_CONTAINS, contains);
	}

	@Test
	public void testIsContains() {
		Location loc1 = new Location(0.0, 0.0, 2);
		Location loc2 = new Location(0.0, 0.0, 1);

		DistanceBasedUtilty helper = new DistanceBasedUtilty(loc1);
		Contains contains = helper.isContains(loc2);

		Assert.assertEquals(Contains.CONTAINS, contains);

	}
}