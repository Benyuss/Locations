package hu.benyuss.geohash.haversineUtils;

//It's better to return an enum rather than a Constant String. 
//Used to know if the location given by user is contains the other locations or not.
public enum Contains {
	CONTAINS, NOT_CONTAINS, SEMI_CONTAINS;
}