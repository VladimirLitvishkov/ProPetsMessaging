package propets.exceptions.messaging;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Post not found")
public class PostIdNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

}
