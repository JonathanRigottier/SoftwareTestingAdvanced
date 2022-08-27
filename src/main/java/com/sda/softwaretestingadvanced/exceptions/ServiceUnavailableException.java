package com.sda.softwaretestingadvanced.exceptions;

/**
 * Exception to handle server unavailability
 *
 * @author Vinod John
 */
public class ServiceUnavailableException extends Exception {
    private static final long serialVersionID = 1L;

    public ServiceUnavailableException (String message) {
        super(message);
    }
}
