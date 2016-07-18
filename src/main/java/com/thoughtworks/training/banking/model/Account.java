package com.thoughtworks.training.banking.model;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Account {
  private final long id;
  private final String number;
  private final String name;
  private final String username;
  private final List<Balance> balances;

  public Account(long id, String number, String name, String username) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.username = username;
    balances = new ArrayList<>();
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
    return balances;
  }

  public void addBalance(Balance balance) {
    balances.add(balance);
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
}
