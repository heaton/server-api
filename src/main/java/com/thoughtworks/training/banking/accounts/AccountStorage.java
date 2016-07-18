package com.thoughtworks.training.banking.accounts;

import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Balance;

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

  public Account findByAccountNumber(String accountNumber) {
    Account account = accounts.get(accountNumber);
    if (account == null) {
      throw new IllegalArgumentException(String.format("Account number '%1$s' does not exist!", accountNumber));
    }
    return account;
  }

  public Balance findAccountBalanceByCurrency(Account account, String currency) {
    Optional<Balance> balanceOptional = account.getBalances()
        .stream()
        .filter(balance -> balance.getCurrency().equals(currency)).findFirst();

    if (!balanceOptional.isPresent()) {
      throw new IllegalArgumentException(String.format("Account number '%1$s' does not exist!", account.getNumber()));
    }
    return balanceOptional.get();
  }
}
