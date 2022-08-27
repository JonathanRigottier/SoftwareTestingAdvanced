package com.sda.softwaretestingadvanced.exceptions;

/**
 * Exception to handle user validation
 *
 * @author Vinod John
 */
public class UserValidationException extends Exception {
    private static final long serialVersionID = 1L;

    public UserValidationException(String name, String message) {
        super(String.format("User validation failed for user: %s, Error: %s", name, message));
    }
}
