package com.thoughtworks.training.banking.model;

import java.math.BigDecimal;

/**
 * Created by yqin on 7/18/16.
 */
public class Balance {
    private final long accountId;
    private final String currency;

    private BigDecimal amount;

    public long getAccountId() {
        return accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Balance(long accountId, String currency) {
        this.accountId = accountId;
        this.currency = currency;
    }

    public Balance(long accountId, String currency, BigDecimal amount) {
        this.accountId = accountId;
        this.currency = currency;
        this.amount = amount;
    }
}


