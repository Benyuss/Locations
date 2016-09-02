package abstractUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import dataModels.Location;

public class CSVScanner {
	// Scans CSV files until their last line. Saves Data as Location data
	// dtructures.

	private ArrayList<Location> container;
	private int lastIndex;

	public Location getIteratedContainer(int i) { // we may pass the whole
													// ArrayList and then save
													// it in another class
													// but passing that data
													// without saving it
													// everytime is fast as
													// lighting.

		return container.get(i);
	}

	public ArrayList<Location> getContainer() {
		return container;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void scan(InputStream file) throws IOException {
		CSVReader scanner = new CSVReader(new InputStreamReader(file)); // InputStreamReader
																		// is
																		// needed
																		// because
																		// it
																		// gives
																		// way
																		// better
																		// abstraction
																		// than
																		// FileReader
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
