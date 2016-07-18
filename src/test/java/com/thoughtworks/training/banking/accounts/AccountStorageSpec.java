package com.thoughtworks.training.banking.accounts;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Balance;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountStorageSpec {

  private AccountStorage accountStorage;

  @Before
  public void given_accounts() {
    Account ac1 = getAccount1();
    Account ac2 = getAccount2();
    Account ac3 = getAccount3();

    accountStorage = new AccountStorage(
        Maps.newHashMap(ImmutableMap.of(
            ac1.getNumber(), ac1,
            ac2.getNumber(), ac2,
            ac3.getNumber(), ac3
        ))
    );
  }

  @Test
  public void should_get_an_empty_list_when_no_username_given() {
    assertThat(accountStorage.findByUser(""), is(Lists.newArrayList()));
  }

  @Test
  public void should_get_accounts_by_given_username() {
    assertThat(accountStorage.findByUser("u1"), hasItems(getAccount1(), getAccount2()));
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  @Test
  public void should_get_account_by_given_existing_account_number() {
    assertThat(accountStorage.findByAccountNumber("3001").get(), is(getAccount3()));
  }

  @Test
  public void should_get_account_by_given_non_existing_account_number() {
    assertThat(accountStorage.findByAccountNumber("4001").isPresent(), is(false));
  }

  private Account getAccount3() {
    Account ac3 = new Account(3L, "3001", "Joy", "u3");
    ac3.update(new Balance(ac3.getId(), "cny", new BigDecimal(20)));
    return ac3;
  }

  private Account getAccount2() {
    Account ac2 = new Account(2L, "2001", "Heaton", "u1");
    ac2.update(new Balance(ac2.getId(), "cny", new BigDecimal(80)));
    return ac2;
  }

  private Account getAccount1() {
    Account ac1 = new Account(1L, "1001", "Heaton", "u1");
    ac1.update(new Balance(ac1.getId(), "cny", new BigDecimal(100)));
    ac1.update(new Balance(ac1.getId(), "usd", new BigDecimal(120)));
    return ac1;
  }

}
