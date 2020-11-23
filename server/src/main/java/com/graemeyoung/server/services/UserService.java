package com.graemeyoung.server.services;

import com.graemeyoung.server.domain.User;
import com.graemeyoung.server.exceptions.AuthException;

public interface UserService {

    User validateUser(String email, String password) throws AuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws AuthException;

    User validateUserById(Integer userId) throws AuthException;

}
