package cz.etnetera.reesmo.restapi.output;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RestApiValidationError {
	
	public List<FieldError> errors = new ArrayList<>();
	
	public RestApiValidationError(MethodArgumentNotValidException e, MessageSource messageSource, Locale locale) {
		e.getBindingResult().getFieldErrors().forEach(err -> {
			errors.add(new FieldError(err.getField(), messageSource.getMessage(err, locale)));
		});
	}
	
	public static class FieldError {
		
		public String field;
		
		public String message;

		public FieldError(String field, String message) {
			this.field = field;
			this.message = message;
		}
		
	}
	
}
