package hu.benyuss.geohash.abstractUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import hu.benyuss.geohash.dataModels.Location;

public class CSVScanner {
	// Scans CSV files until their last line. Saves Data as Location data structures.

	private ArrayList<Location> container; 
	private int lastIndex;

	public Location getIteratedContainer(int i) { //fillDBWithCSV method use that. 
													//you dont have to save that data every time (in a loop) because of that method.
		return container.get(i);
	}

	public ArrayList<Location> getContainer() {
		return container;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void scan(InputStream file) throws IOException {
		CSVReader scanner = new CSVReader(new InputStreamReader(file)); //using inputstreamreader because it's more abstract than FileReader
		
		String[] coordinates;
		container = new ArrayList<Location>();

		while ((coordinates = scanner.readNext()) != null) {
			Location location = new Location((Double.parseDouble(coordinates[0])), (Double.parseDouble(coordinates[1])),
					(Integer.parseInt(coordinates[2])));
			container.add(location);
			lastIndex = container.size();
		}
		scanner.close();
	}
}
