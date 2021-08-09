package expense.restcontroller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import expense.model.Events;
import expense.model.User;
import expense.repository.EventRepository;
import expense.service.EventService;

@RestController
@CrossOrigin
@RequestMapping("/api/events")
public class EventController {
	
	
	@Autowired
	EventRepository eventrepo;
	
	@Autowired 
	EventService eventservice;
	
	
	@GetMapping ("")
	
	public ResponseEntity<List<Events>>  getAllEvents(HttpServletRequest request) {
		
		Long userId= (Long) request.getAttribute("userId");
		User usr =eventservice.getUserById(userId);
		List<Events> events=eventservice.gellAllUserEvents(usr);
		
		return new ResponseEntity<>(events, HttpStatus.OK); 
		
	}
	@GetMapping ("/{eventId}")
	public ResponseEntity<Events> getEventbyUserandEvent(HttpServletRequest request,@PathVariable("eventId") Long eventId) {
		Long userId= (Long) request.getAttribute("userId");
		User usr =eventservice.getUserById(userId);
		return new ResponseEntity<>(eventservice.getEventByIdandUser(usr, eventId),HttpStatus.OK);
		
	}
	
	@PostMapping("")
	public Events postEvent(@RequestBody Map<Object, String> eventMap,HttpServletRequest request) throws ParseException {
		String name=eventMap.get("name");
		String description=eventMap.get("description");
		String date=eventMap.get("eventDay");
		Long userId= (Long) request.getAttribute("userId");
		User usr =eventservice.getUserById(userId);
		System.out.println("tv :"+name+"description :"+description+"date :"+date+"usr"+usr);
		return eventservice.createPost(name, description,date,usr);
		
	}
	
	 @DeleteMapping("/{eventId}")
	    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
	                                                               @PathVariable("eventId") Long eventId) {
		 	Long userId= (Long) request.getAttribute("userId");
			User usr =eventservice.getUserById(userId);
			eventservice.removeCategoryWithAllTransactions(usr, eventId);
	        Map<String, Boolean> map = new HashMap<>();
	        map.put("success", true);
	        return new ResponseEntity<>(map, HttpStatus.OK);
	    }
	 
	   @PutMapping("/update/{eventId}")
	    public ResponseEntity<Events> updateCategory(HttpServletRequest request,
	                                                               @PathVariable("eventId") Long eventId,
	                                                               @RequestBody Map<Object, String> eventMap) throws ParseException {
		   	Long userId= (Long) request.getAttribute("userId");
		   	System.out.println("userId"+userId);
			User usr =eventservice.getUserById(userId);
			String name=eventMap.get("name");
			String description= eventMap.get("description");
			String date= eventMap.get("eventDay");
		
			Events evnt=eventservice.findEventbyIdandbyUserandUpdate(usr, eventId, name,description,date);
//	        Map<String, Boolean> map = new HashMap<>();
//	        map.put("success", true);
	        return new ResponseEntity<>(evnt, HttpStatus.OK);
	    }
	
	

}
