package com.pirates.frts.error;

import com.pirates.frts.util.Firebase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotRegisteredException extends Exception{
    protected static final Logger LOGGER = LoggerFactory.getLogger(UserNotRegisteredException.class);

    private static final long serialVersionUID = 1L;

    public UserNotRegisteredException( String message ) {
        super(message);
    }

    public UserNotRegisteredException(String message, Throwable cause ) {
        super( message, cause );
    }
}
