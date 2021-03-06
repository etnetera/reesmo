package cz.etnetera.reesmo.restapi;

import cz.etnetera.reesmo.restapi.output.RestApiValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

public class AbstractRestController {
	
	@Autowired
	protected MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestApiValidationError processValidationError(MethodArgumentNotValidException e, Locale locale) {
        return new RestApiValidationError(e, messageSource, locale);
    }
	
}
