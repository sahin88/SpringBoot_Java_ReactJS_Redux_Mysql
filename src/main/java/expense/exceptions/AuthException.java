package expense.exceptions;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(code=HttpStatus.UNAUTHORIZED)


public class AuthException extends RuntimeException {
	
	
    public AuthException(String message) {
        super(message);
    }

}
