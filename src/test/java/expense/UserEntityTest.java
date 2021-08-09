package expense;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import expense.model.Events;
import expense.model.User;
import expense.repository.EventRepository;
import expense.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserEntityTest {
	
	  @Autowired
	    private TestEntityManager entityManager;
	     
	    @Autowired
	    private UserRepository repo;
	    @Autowired 
	    private EventRepository eventrepo;
	    
	     
	    // test methods go below
	    
	    @Test
	    public void testCreateUser() throws ParseException {
//	        User user = new User();
//	        user.setEmail("johndoe@gmail.com");
//	        user.setPassword("johndoe123");
//	        user.setFirstName("John");
//	        user.setLastName("Doe");
//	         
//	        User savedUser = repo.save(user);
//	         
//	        User existUser = entityManager.find(User.class, savedUser.getId());
	        
	        User loginuser=repo.findUserByEmail("mail1@mail.com");
	        
	        String str_date = "11/12/07";
	        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date date = formatter.parse(str_date);
	        System.out.println("date"+date+loginuser);
	        
	        
	        Events evnt= new Events();
	        evnt.setDescription("Jesicas Birthday");
	        evnt.setUser(loginuser);
	        evnt.setEventDay(date);
	        evnt.setName("Jesicas Birthday Eventssss");
	        Events savedevent=eventrepo.save(evnt);
	        System.out.println("loginuser :"+savedevent);
//	        
	        
	        assertThat(1>2);
	         
	    }

}




