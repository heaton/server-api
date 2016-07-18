package com.thoughtworks.training.banking.accounts;

import com.thoughtworks.training.banking.model.Account;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountStorage {

  private final Map<String, Account> accounts;

  public AccountStorage(Map<String, Account> accounts) {
    this.accounts = accounts;
  }

  public List<Account> findByUser(String username) {
    return accounts.values().stream()
        .filter(account -> account.getUsername().equals(username))
        .sorted((ac1, ac2) -> Long.compare(ac1.getId(), ac2.getId()))
        .collect(Collectors.toList());
  }

  public Optional<Account> findByAccountNumber(String accountNumber) {
    return accounts.values().stream().filter(account -> account.getNumber().equals(accountNumber)).findFirst();
  }
}
