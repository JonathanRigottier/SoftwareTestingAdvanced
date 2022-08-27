package com.sda.softwaretestingadvanced.models;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Model for user
 *
 * @author Vinod John
 */
@Data
public class User {
    private String firstName;
    private String lastName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
