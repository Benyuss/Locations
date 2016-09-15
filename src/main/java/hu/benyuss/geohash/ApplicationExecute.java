package hu.benyuss.geohash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
@ComponentScan
@EnableWebSecurity
public class ApplicationExecute {
	public static void main(String[] args) throws Exception { 
		// start Spring server.	
		SpringApplication.run(ApplicationExecute.class,args);
	}
}
