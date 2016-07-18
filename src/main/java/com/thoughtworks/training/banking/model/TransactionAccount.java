package com.thoughtworks.training.banking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionAccount {
    private final String accountNumber;
    private final String currency;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    @JsonCreator
    public TransactionAccount(@JsonProperty("accountNumber") String accountNumber, @JsonProperty("currency") String currency) {
        this.accountNumber = accountNumber;
        this.currency = currency;
    }
}
