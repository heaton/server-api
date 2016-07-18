package com.thoughtworks.training.banking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by yqin on 7/18/16.
 */
public class Transaction {
    private final TransactionAccount fromAccount;
    private final TransactionAccount toAccount;
    private final BigDecimal amount;

    @JsonCreator
    public Transaction(@JsonProperty("fromAccount") TransactionAccount fromAccount, @JsonProperty("toAccount") TransactionAccount toAccount, @JsonProperty("amount") BigDecimal amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public TransactionAccount getFromAccount() {
        return fromAccount;
    }

    public TransactionAccount getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
