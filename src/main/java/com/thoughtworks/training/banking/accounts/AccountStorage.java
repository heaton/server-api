package com.thoughtworks.training.banking.accounts;

import com.thoughtworks.training.banking.model.Account;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

  public void updateAccount(String accountNumber, String currency, BigDecimal amount) {
    findByAccountNumber(accountNumber).update(currency, amount);
  }

  Account findByAccountNumber(String accountNumber) {
    Account account = accounts.get(accountNumber);
    Assert.notNull(account, String.format("Account number '%1$s' does not exist!", accountNumber));
    return account;
  }

}
