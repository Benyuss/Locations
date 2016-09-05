package hu.benyuss.geohash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.benyuss.geohash.webserver.*;

@SpringBootApplication
public class ApplicationExecute {
	public static void main(String[] args) throws Exception { 
		// start Spring server.	
		SpringApplication app = new SpringApplication(GeneralController.class, LoginController.class);
		app.run(args);
	}
}
