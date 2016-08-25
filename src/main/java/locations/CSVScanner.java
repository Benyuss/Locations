package locations;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class CSVScanner {
	// Scans CSV files until their last line. Saves data in Tuples (LOOK AT ->
	// class Tuple) to an ArrayList.

	private ArrayList<Tuple> container;
	private int lastIndex;

	public Tuple getIteratedContainer(int i) {
		return container.get(i);
	}

	public ArrayList<Tuple> getContainer() {
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
																		// it's
																		// give
																		// way
																		// better
																		// abstraction
																		// level
																		// than
																		// FileReader.
		String[] coordinates;
		container = new ArrayList<Tuple>();

		while ((coordinates = scanner.readNext()) != null) {
			Tuple tuple = new Tuple(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]),
					Integer.parseInt(coordinates[2]));
			container.add(tuple);
			lastIndex = container.size();
		}
		scanner.close();
	}
}
