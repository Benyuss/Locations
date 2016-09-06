package hu.benyuss.geohash.dataModels;

import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {

	private static final Logger logger = (Logger) LogManager.getLogger(User.class.getName());
	
	private String firstName;
	private String lastName;
	private String email;
	private String nickname;
	private String passwordValidator;
	private String password;

	public User() {
	}
	
	public User(String first, String last, String mail, String nick, String pwvalid, String pw) {
		setFirstName(first);
		setLastName(last);
		setEmail(mail);
		setNickname(nick);
		setPasswordValidator(pwvalid);
		setPassword(pw);
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

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPasswordValidator() {
		return passwordValidator;
	}
	
	public void setPasswordValidator(String passwordValidator) {
		this.passwordValidator = passwordValidator;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}


