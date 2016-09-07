package hu.benyuss.geohash.dataModels;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.validator.constraints.Email;

public class User {

	private static final Logger logger = (Logger) LogManager.getLogger(User.class.getName());
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size (min = 6, max = 100)
	private String nickname;
	@NotNull
	@Size(min = 8, max = 16)
	private String passwordValidator;
	@NotNull
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


