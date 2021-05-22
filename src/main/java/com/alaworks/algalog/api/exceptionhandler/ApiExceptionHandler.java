package com.alaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alaworks.algalog.domain.exception.EntityNotFoundException;
import com.alaworks.algalog.domain.exception.NegotialException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;

	public ApiExceptionHandler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problema.Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			
			String name = ((FieldError) error).getField();
			
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale()); 
			
			campos.add(new Problema.Campo(name, message));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDateTime(OffsetDateTime.now());
		problema.setTitle("Um ou mais campos estão inválidos. Preencha corretamente e tente novamente!");
		problema.setCampos(campos);

		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDateTime(OffsetDateTime.now());
		problema.setTitle(ex.getMessage());
		
				
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request); 
	}
	
	@ExceptionHandler(NegotialException.class)
	public ResponseEntity<Object> handleNegotial(NegotialException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDateTime(OffsetDateTime.now());
		problema.setTitle(ex.getMessage());
		
				
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request); 
	}
}
