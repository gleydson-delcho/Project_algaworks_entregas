package com.alaworks.algalog.domain.exception;

public class EntityNotFoundException extends NegotialException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
