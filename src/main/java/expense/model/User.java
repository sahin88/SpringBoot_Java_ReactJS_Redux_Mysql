package expense.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	private String firstName;
	private String lastName;
	private String password;
	
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Events> event= new ArrayList<>();
	
	
	public User(Long id, String firstName, String lastName, String password, String email, List<Events> event) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.event = event;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Events> getEvent() {
		return event;
	}

	public void setEvent(List<Events> event) {
		this.event = event;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password) ;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email
				+ "]";
	}
	
	

}
