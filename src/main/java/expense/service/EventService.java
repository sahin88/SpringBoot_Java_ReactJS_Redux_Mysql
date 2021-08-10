package expense.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import expense.model.Events;
import expense.model.User;
import expense.repository.EventRepository;
import expense.repository.UserRepository;
@Service
@Transactional
public class EventService {
	
	@Autowired 
	EventRepository eventrepo;
	
	
	@Autowired
	UserRepository userrepo;
	
	
	public Events createPost(String name,String description, String date,User user) throws ParseException {
		Events event= new Events();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date str_to_date = formatter.parse(date);
        event.setDescription(description);
        event.setEventDay(str_to_date);
        event.setName(name);
        event.setUser(user);
		Events saved_user =eventrepo.save(event);
		return saved_user;
		
	}
	
	public User  getUserById(Long userId) {
		return userrepo.findUserById(userId);
		
	}
	
	public List<Events> gellAllUserEvents(User usr) {
		return eventrepo.findAllEventsofUser(usr);
	}


	
	
	public Events getEventByIdandUser(User user, Long id) {
		
		return eventrepo.findEventbyIdandbyUser(user, id);
	}
	

	public void removeCategoryWithAllTransactions(User userId, Long eventId) {
		eventrepo.findEventbyIdandbyUserandDelete(userId, eventId);
		
	}
	
	public Events findEventbyIdandbyUserandUpdate(User user, Long eventId,String name, String description,String date) throws ParseException{
		
		Events from_db=eventrepo.findEventbyIdandbyUser(user, eventId);
		
		from_db.setDescription(description);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date str_to_date = formatter.parse(date);
		from_db.setEventDay(str_to_date);
		from_db.setName(name);
		return eventrepo.save(from_db);
 
	 }

		
		 
		 
				
	 }
	

