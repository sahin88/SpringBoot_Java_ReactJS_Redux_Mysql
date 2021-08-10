package expense.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Events {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String Description;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date eventDay;
	
	
	@ManyToOne( fetch=FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private User user;

	
	public Events() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Events(Long id, String name, String description, Date eventDay, User user) {
		super();
		this.id = id;
		this.name = name;
		Description = description;
		this.eventDay = eventDay;
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public Date getEventDay() {
		return eventDay;
	}


	public void setEventDay(Date eventDay) {
		this.eventDay = eventDay;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	

}
