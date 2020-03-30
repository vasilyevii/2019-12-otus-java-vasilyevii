package com.vasilyevii.core.service;

import com.vasilyevii.core.model.User;

import java.util.Optional;

public interface DBServiceUser {

  long saveUser(User user);

  Optional<User> getUser(long id);

}
