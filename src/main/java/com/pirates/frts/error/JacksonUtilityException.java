package com.pirates.frts.error;

import com.pirates.frts.util.Firebase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JacksonUtilityException extends Throwable {

	protected static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtilityException.class);
	
	private static final long serialVersionUID = 1L;

	public JacksonUtilityException( String message ) {
		super( message );
	}
	
	public JacksonUtilityException(String message, Throwable cause ) {
		super( message, cause );
	}
	
}
