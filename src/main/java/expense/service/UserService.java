package expense.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import expense.exceptions.AuthException;
import expense.model.User;
import expense.repository.UserRepository;


@Service
@Transactional
public class  UserService {
	
	@Autowired
	UserRepository userrepo;


	    public User validateUser(String email, String password) throws AuthException{
	    	System.out.println(email+"   :::"+ password);
	    	try {
	    		User user= userrepo.findUserByEmail(email);
	    		System.out.println("user from service ::"+user);
		    	if (!BCrypt.checkpw(password,user.getPassword())){
		    		throw new AuthException("Invalid Password ");
		    		
	    		
	    	}
		    	return user;
	    	}catch (EmptyResultDataAccessException e) {
	    		throw new AuthException("Password or User invalid ");}
	    		
	  }

	   public  User registerUser(String firstName, String lastName, String email, String password) throws AuthException{
	    	
	    	 Pattern pattern = Pattern.compile("^(.+)@(.+)$");
	         if(email != null) email = email.toLowerCase();
	         if(!pattern.matcher(email).matches())
	             throw new AuthException("Invalid email format");
	    	
	        int  checkuser= userrepo.findUserByEmailBoolean(email);
	    	if (checkuser>0 ) {
	    		throw new AuthException("Email has been token ");
	    	}
	    	
	    	User user = new User();
	    	user.setEmail(email);
	    	user.setFirstName(firstName);
	    	user.setLastName(lastName);
	    	user.setPassword(password);
	    	userrepo.save(user);
			return user;
	    	
	    };

}
	
	
