package com.thoughtworks.training.banking.accounts;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Balance;

import java.math.BigDecimal;
import java.util.Map;

public class AccountStorageFactory {

  public AccountStorage generate() {
    return new AccountStorage(memoryData());
  }

  private Map<String, Account> memoryData() {
    Account heaton1 = new Account(1L, "1001", "Heaton", "heaton");
    heaton1.addBalance(new Balance(heaton1.getId(), "cny", new BigDecimal(100000)));
    heaton1.addBalance(new Balance(heaton1.getId(), "usd", new BigDecimal(30000)));
    Account heaton2 = new Account(2L, "2001", "Heaton", "heaton");
    heaton2.addBalance(new Balance(heaton2.getId(), "cny", new BigDecimal(200000)));
    Account qinyu = new Account(3L, "1002", "Qinyu", "qinyu");
    qinyu.addBalance(new Balance(qinyu.getId(), "cny", new BigDecimal(80000)));
    qinyu.addBalance(new Balance(qinyu.getId(), "usd", new BigDecimal(0)));

    return Maps.newHashMap(ImmutableMap.of(
        heaton1.getNumber(), heaton1,
        heaton2.getNumber(), heaton2,
        qinyu.getNumber(), qinyu));
  }

}
