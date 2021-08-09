package expense.restcontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import expense.model.User;
import expense.service.UserService;
import expense.jwtconstants.JWTConstants;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserResponse {
	
	@Autowired
	UserService userservice;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<Object, String> userMap) {
		
		String firstName=userMap.get("firstName");
		String lastName=userMap.get("lastName");
		String email=userMap.get("email");
		String password= userMap.get("password");
		User user= userservice.registerUser(firstName, lastName,  email, password);
		Map<String, String> map= new HashMap<>();
		map.put("message", "registered successfully!");
		return  new ResponseEntity<>(map,HttpStatus.OK);
				
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<Object, String> userMap) {

		String email=userMap.get("email");
		String password= userMap.get("password");
		System.out.println(email+password);
		User user= userservice.validateUser(email, password);
		Map<String, String> map= new HashMap<>();
		map.put("message", " login successfully!");
		return  new ResponseEntity<>(generateJWTToken(user),HttpStatus.OK);
				
	}
	
	
	  private Map<String, String> generateJWTToken(User user) {
	        long timestamp = System.currentTimeMillis();
	        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWTConstants.API_SECRET_KEY)
	                .setIssuedAt(new Date(timestamp))
	                .setExpiration(new Date(timestamp + JWTConstants.TOKEN_VALIDITY))
	                .claim("userId", user.getId())
	                .claim("email", user.getEmail())
	                .claim("firstName", user.getFirstName())
	                .claim("lastName", user.getLastName())
	                .compact();
	        Map<String, String> map = new HashMap<>();
	        map.put("token", token);
	        return map;
	    }

}
