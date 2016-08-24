package locations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import com.opencsv.CSVReader;

public class CSVScanner {
	// Scans CSV files until their last line. Saves data in Tuples (LOOK AT ->
	// class Tuple) to an ArrayList.

	private static Logger logger = null;
	static {

		try {
			InitLogger.initialize();
		} catch (FileNotFoundException e) {
			logger.log(Level.ERROR,
					"Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.ERROR,
					"Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		}

		logger = InitLogger.logger[0];
	}

	private ArrayList<Tuple> container;
	private int lastIndex;

	public void scan(InputStream file) throws Exception {
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

	public Tuple getIteratedContainer(int i) {
		return container.get(i);
	}

	public ArrayList<Tuple> getContainer() {
		return container;
	}

	public int getLastIndex() {
		return lastIndex;
	}

}
