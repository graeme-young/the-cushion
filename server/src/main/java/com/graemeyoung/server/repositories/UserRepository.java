package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.User;

public interface UserRepository {

    Integer create(String email, String password, String firstName, String lastName);

    User findByEmailAndPassword(String email, String password);

    Integer getCountByEmail(String email);

    User findById(Integer userId);

}
