package com.sda.softwaretestingadvanced.services;

import com.sda.softwaretestingadvanced.exceptions.ServiceUnavailableException;
import com.sda.softwaretestingadvanced.models.User;

/**
 * Service to handle user related operations
 *
 * @author Vinod John
 */
public interface UserService {
    /**
     * To fetch a user form a remote server
     *
     * @return User
     */
    User getUserFromRemoteServer() throws ServiceUnavailableException;
}
