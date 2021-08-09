package expense.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;
import expense.jwtconstants.JWTConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.FilterChain;

public class AuthFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
	     HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
	     
	     
	     String authHeader = httpRequest.getHeader("Authorization");
//	     System.out.println("httpResponse   ------     "+authHeader);
//	     Enumeration<String> headerNames = httpRequest.getHeaderNames();
//	     while (headerNames.hasMoreElements()) {
//			String headerName = headerNames.nextElement();
//			System.out.print("Header Name " + headerName);
//			String headerValue = httpRequest.getHeader(headerName);
//			System.out.print("header Value" + headerValue);
//			System.out.println("---");}
	     
	     
	     if(authHeader != null) {
	    	 String [] authHeaderArr= authHeader.split("Bearer ");
	    	
	    	  
	    	
	    	 if(authHeaderArr.length > 1 && authHeaderArr[1] != null) {
	                String token = authHeaderArr[1];
	                
	               
	             
	                try {
	                    Claims claims = Jwts.parser().setSigningKey(JWTConstants.API_SECRET_KEY)
	                            .parseClaimsJws(token).getBody();
	    
	                    httpRequest.setAttribute("userId", Long.parseLong(claims.get("userId").toString()));
	                }catch (Exception e) {
	                	System.out.println("authheader :"+token);
	                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
	                    return;
	                }
	            } else {
	                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
	                return;
	            }
	    	 
	     }
	     else {
	    	 httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
	            return;
	    	 
	     }
	     chain.doFilter(servletRequest, servletResponse);
		
		
	}

}
