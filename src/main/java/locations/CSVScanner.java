package locations;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class CSVScanner {
	// Scans CSV files until their last line. Saves data in Tuples (LOOK AT ->
	// class Tuple) to an ArrayList.

	private ArrayList<PairedDataStructure> container;
	private int lastIndex;

	public PairedDataStructure getIteratedContainer(int i) {
		return container.get(i);
	}

	public ArrayList<PairedDataStructure> getContainer() {
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
		container = new ArrayList<PairedDataStructure>();

		while ((coordinates = scanner.readNext()) != null) {
			PairedDataStructure tuple = new PairedDataStructure(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]),
					Integer.parseInt(coordinates[2]));
			container.add(tuple);
			lastIndex = container.size();
		}
		scanner.close();
	}
}
