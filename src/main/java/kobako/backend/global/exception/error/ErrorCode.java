package kobako.backend.global.exception.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

	String getMessage();

	HttpStatus getStatus();

	String getCode();
}
