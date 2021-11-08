package com.restfulapi.restful.exception;

public class ResourceNotFoundException extends Exception {
	private static final long serialVVersionUID=1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
