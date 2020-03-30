package com.vasilyevii.core.dao;

import com.vasilyevii.core.model.User;
import com.vasilyevii.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {
  Optional<User> findById(long id);

  long saveUser(User user);

  SessionManager getSessionManager();

}
