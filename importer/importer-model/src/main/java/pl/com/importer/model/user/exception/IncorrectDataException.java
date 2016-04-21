package pl.com.importer.model.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IncorrectDataException extends RuntimeException {

    public IncorrectDataException() {
        super("Incorrect data exception!");
    }

    public IncorrectDataException(final String message) {
        super(message);
    }

    public IncorrectDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
