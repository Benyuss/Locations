package locations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class InitLogger {

	final static Logger logger[] = new Logger[1];

	static void initialize() throws FileNotFoundException, IOException {

		ConfigurationFactory factory = XmlConfigurationFactory.getInstance();
		ConfigurationSource configurationSource = new ConfigurationSource(
				new FileInputStream(new File("Configuration.xml")));
		Configuration configuration = factory.getConfiguration(configurationSource);
		ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());
		// Add console appender into configuration
		configuration.addAppender(appender);
		// Create loggerConfig
		LoggerConfig loggerConfig = new LoggerConfig("com", Level.FATAL, false);
		// Add appender
		loggerConfig.addAppender(appender, null, null);
		// Add logger and associate it with loggerConfig instance
		configuration.addLogger("com", loggerConfig);
		// Get context instance
		LoggerContext context = new LoggerContext("aNewLoggerContext");
		// Start logging system
		context.start(configuration);
		logger[0] = context.getLogger("com");
	}
}