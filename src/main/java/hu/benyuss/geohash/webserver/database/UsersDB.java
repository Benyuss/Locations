package hu.benyuss.geohash.webserver.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UsersDB {
	
	//TODO @valid hova?
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	
	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String nickname;
	
	private String password;

	public UsersDB() {
	}
	
	public UsersDB (String first, String last, String mail, String nick, String pw) {
		setFirstName(first);
		setLastName(last);
		setEmail(mail);
		setNickname(nick);
		setPassword(pw);
	}

	public Long getId() {
		return id;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}