package com.thoughtworks.training.banking.model;

import com.google.common.base.Objects;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Account {
  private final long id;
  private final String number;
  private final String name;
  private final String username;
  private final Map<String, Balance> balances;

  public Account(long id, String number, String name, String username) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.username = username;
    this.balances = new HashMap<>();
  }

  public long getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public List<Balance> getBalances() {
    return balances.values().stream()
        .sorted((ba1, ba2) -> ba1.getCurrency().compareTo(ba2.getCurrency()))
        .collect(Collectors.toList());
  }

  public void update(Balance balance) {
    balances.put(balance.getCurrency(), balance);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return id == account.id &&
        Objects.equal(number, account.number) &&
        Objects.equal(name, account.name) &&
        Objects.equal(username, account.username) &&
        Objects.equal(balances, account.balances);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, number, name, username, balances);
  }

  public void update(String currency, BigDecimal amount) {
    final Balance balance = balances.get(currency);
    Assert.notNull(balance, "can not find balance in currency " + currency);
    update(balance.copy(balance.getAmount().add(amount)));
  }

}
