package hu.benyuss.geohash.webserver.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hu.benyuss.geohash.geoLocations.LocationCalculator;

@Entity
@Table(name = "users")
public class UsersDB {
	
	private static final Logger logger = (Logger) LogManager.getLogger(UsersDB.class.getName());

	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:mem:locationdb"; //in memory database.

	static final String USER = "sa";
	static final String PASS = "";

	@Id
	private String nickname;
	private String password;
	private String passwordValidator;
	private String firstName;
	private String lastName;
	private String email;

	public UsersDB() {
	}
	
	public UsersDB (String nick, String pw, String first, String last, String mail) {
		setNickname(nick);
		setPassword(pw);
		setFirstName(first);
		setLastName(last);
		setEmail(mail);
	}

	public String getNickname() {
		return nickname;
	}
	
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}
	
	
	public boolean setPassword(String password) { //TODO konstuktor valahonnan hívodik. Ezáltal arg üres. NPEXC! 
		
		boolean valueCheck = password == passwordValidator;
		
		if (valueCheck == true) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		this.password = hashedPassword;
		}
		else {
			logger.info("pwValidator -> " + passwordValidator + "pw -> " + password );
//			throw new IllegalArgumentException("Passwords have to be the same.");
		}
		
		return valueCheck;
	}
	

	public String getPasswordValidator() {
		return passwordValidator;
	}
	
	
	public void setPasswordValidator(String passwordValidator) {
		this.passwordValidator = passwordValidator;
	}

	public String getFirstName() {
		return firstName;
	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}

}