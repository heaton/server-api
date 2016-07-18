package com.thoughtworks.training.banking.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqin on 7/18/16.
 */
public class Account {
    private final long id;
    private final String number;
    private final String name;
    private final String userName;
    private List<Balance> balances;

    public Account(long id, String number, String name, String userName) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void addBalance(Balance balance) {
        balances.add(balance);
    }
}
