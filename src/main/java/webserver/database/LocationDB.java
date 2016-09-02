package webserver.database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LocationDB {
	
	static final String JDBC_DRIVER = "org.h2.Driver"; // org.h2.Driver
	static final String DB_URL = "jdbc:h2:mem:locationdb";

	static final String USER = "sa";
	static final String PASS = "";

	@Id
	private String geohash;
	private Double latitude;
	private Double longitude;
	private int radius;

	public LocationDB() {
	}

	public LocationDB(Double latitude, Double longitude, int radius, String geohash) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.geohash = geohash;
	}

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geohash == null) ? 0 : geohash.hashCode());
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
		LocationDB other = (LocationDB) obj;
		if (geohash == null) {
			if (other.geohash != null)
				return false;
		} else if (!geohash.equals(other.geohash))
			return false;
		return true;
	}
	
//	public ArrayList<PairedData> findall () throws SQLException, ClassNotFoundException{
//		
//		Class.forName("org.h2.Driver");
//	    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//	    PreparedStatement ps = con.prepareStatement("SELECT * FROM LOCATIONDB;");
//	    ResultSet rs = ps.executeQuery();
//
//	    ArrayList<PairedData> fkdplist = new ArrayList<PairedData>();
//	    
//	    while(rs.next())
//	    {
//	    	PairedData data = new PairedData();
//	    	data.setGeoHash(rs.getString(1));
//	    	
//	    	Location loc = new Location ((Double)Double.parseDouble(rs.getString(2)),  (Double)Double.parseDouble(rs.getString(3)), Integer.parseInt(rs.getString(4)));
//	    	
//	    	data.setLoc(loc);
//	    	fkdplist.add(data);
//	    }
//
//	    return fkdplist;
//	}
	
}