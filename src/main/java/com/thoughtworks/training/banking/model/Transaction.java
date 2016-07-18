package com.thoughtworks.training.banking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Transaction {
    private final TransactionAccount from;
    private final TransactionAccount to;
    private final BigDecimal amount;

    @JsonCreator
    public Transaction(@JsonProperty("from") TransactionAccount fromAccount, @JsonProperty("to") TransactionAccount toAccount, @JsonProperty("amount") BigDecimal amount) {
        this.from = fromAccount;
        this.to = toAccount;
        this.amount = amount;
    }

    public TransactionAccount getFrom() {
        return from;
    }

    public TransactionAccount getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
