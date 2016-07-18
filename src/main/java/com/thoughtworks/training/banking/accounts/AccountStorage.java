package com.thoughtworks.training.banking.accounts;

import com.thoughtworks.training.banking.model.Account;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountStorage {

  private final Map<String, Account> accounts;

  public AccountStorage(Map<String, Account> accounts) {
    this.accounts = accounts;
  }

  public List<Account> findByUser(String username) {
    return accounts.values().stream().filter(account -> account.getUsername().equals(username)).collect(Collectors.toList());
  }

}
