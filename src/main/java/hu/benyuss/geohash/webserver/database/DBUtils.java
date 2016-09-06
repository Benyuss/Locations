package hu.benyuss.geohash.webserver.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hu.benyuss.geohash.dataModels.User;



public class DBUtils {

	private static final Logger logger = (Logger) LogManager.getLogger(DBUtils.class.getName());
	
	
	public final String encodePW (String password, String passwordValidator) {
		boolean valueCheck = (password.equals(passwordValidator));
		String hashedPW;
		
		if (valueCheck == true) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hashedPW = passwordEncoder.encode(password);
			}
		else {
			logger.info("pwValidator -> " + passwordValidator + "pw -> " + password );
			throw new IllegalArgumentException("Passwords have to be the same.");
		}
		return hashedPW;
	}
	
	
	@Autowired
	private UsersDBRepository repository;
	
	public final void isExisting (User user) {
		
		if (repository.findOne(user.getNickname()) != null ) {
			throw new IllegalArgumentException("This nickname is already existing. Choose another one!");
		}
	}
	
	public boolean login (String currentPW, String realPW) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(currentPW, realPW);
	}
}
