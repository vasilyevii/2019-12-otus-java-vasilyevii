package ru.otus.core.service.account;

import ru.otus.core.model.Account;

import java.util.Optional;

public interface DBServiceAccount {

  long saveAccount(Account user);

  long updateAccount(Account user);

  Optional<Account> getAccount(long id);

}
