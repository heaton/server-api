package com.thoughtworks.training.banking.model;

import com.google.common.base.Objects;

import java.math.BigDecimal;

public class Balance {

  private final long accountId;
  private final String currency;

  private final BigDecimal amount;

  public Balance(long accountId, String currency, BigDecimal amount) {
    this.accountId = accountId;
    this.currency = currency;
    this.amount = amount;
  }

  public long getAccountId() {
    return accountId;
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Balance balance = (Balance) o;
    return accountId == balance.accountId &&
        Objects.equal(currency, balance.currency) &&
        Objects.equal(amount, balance.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(accountId, currency, amount);
  }

  public Balance copy(BigDecimal amount) {
    return new Balance(accountId, currency, amount);
  }

}


