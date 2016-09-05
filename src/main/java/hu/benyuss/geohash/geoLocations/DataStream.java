package hu.benyuss.geohash.geoLocations;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.web.multipart.MultipartFile;

import hu.benyuss.geohash.abstractUtils.CSVScanner;
import hu.benyuss.geohash.dataModels.PairedData;

public class DataStream {

	private static final Logger logger = (Logger) LogManager.getLogger(DataStream.class.getName());

	public ArrayList<PairedData> forwardData(MultipartFile file) {

		CSVScanner scanner = new CSVScanner(); // file data parser. If no file
												// is selected, it will choose
												// coordinates.csv from local
												// machine.
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				InputStream inputStream = new ByteArrayInputStream(bytes);
				scanner.scan(inputStream);
			} catch (IOException e) {
				logger.error("Can't parse data. Full stack trace: ", e);
			}
		} else { // if no file is selected, that one will be chosen.
			try {
				File file1 = new File("coordinates.csv");
				FileInputStream input = new FileInputStream(file1);
				scanner.scan(input);
			} catch (IOException e) {
				logger.error("Can't parse default csv file. Full stack trace: ", e);
			}
		}
		LocationCalculator locationCalc = new LocationCalculator();

		ArrayList<PairedData> resultSet = locationCalc.fillDBWithCSV(scanner);
		
		if (resultSet.isEmpty() == false) {
			return resultSet;
		}
		else {
			throw new IllegalArgumentException("List isn't filled.");
		}
		
	}
}
