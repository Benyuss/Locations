package geoLocations;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.web.multipart.MultipartFile;

import abstractUtils.CSVScanner;
import dataModels.PairedData;

public class DataStream {

	static final Logger logger = (Logger) LogManager.getLogger(DataStream.class.getName());

	public ArrayList<PairedData> forwardData(MultipartFile file, PairedData userInput) {

		CSVScanner scanner = new CSVScanner(); // file data parser. If no file
												// is selected, it will choose
												// coordinates.csv from local
												// machine.
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// validateFile(file);
				InputStream inputStream = new ByteArrayInputStream(bytes);
				scanner.scan(inputStream);
			} catch (IOException e) {
				logger.error("Can't parse data. Full stack trace: ", e);
			}
		} else {
			try {
				File file1 = new File("coordinates.csv");
				FileInputStream input = new FileInputStream(file1);
				scanner.scan(input);
			} catch (IOException e) {
				logger.error("Can't parse default csv file. Full stack trace: ", e);
			}
		}

		LocationCalculator locationCalc = new LocationCalculator();
		return locationCalc.calculate(scanner, userInput);
	}
}
