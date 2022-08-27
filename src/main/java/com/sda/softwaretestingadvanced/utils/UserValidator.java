package com.sda.softwaretestingadvanced.utils;

import com.sda.softwaretestingadvanced.exceptions.UserValidationException;
import com.sda.softwaretestingadvanced.models.User;
import com.sda.softwaretestingadvanced.models.UserType;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

/**
 * Validation utils for user
 *
 * @author Vinod John
 */
public class UserValidator {
    public boolean isAdminUser(User user) throws UserValidationException {
        String fullName = user.getFirstName() + " " + user.getLastName();

        if(user.getUserType() == null) {
            throw new NullPointerException(String.format("The user type is null for user: %s", fullName));
        }

        if(UserType.ADMIN.equals(user.getUserType())) {
            return true;
        } else {
            throw new UserValidationException(fullName, "User admin check failed");
        }
    }

    public String encodePassword (String password) {
        int midIndex = password.length() / 2;
        return password.substring(0, midIndex) + "#sda_java#" + password.substring(midIndex);
    }
}
