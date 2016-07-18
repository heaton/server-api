package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.accounts.AccountStorage;
import com.thoughtworks.training.banking.accounts.AccountStorageFactory;
import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Transaction;
import com.thoughtworks.training.banking.model.TransactionAccount;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by yqin on 7/18/16.
 */
public class TransferControllerSpec {

  private AccountStorage storage;

  @Before
  public void setUp() {
    storage = new AccountStorageFactory().generate();
  }


  @Test
  public void should_transfer_from_one_account_to_other_account_of_same_user() {
    TransferController transferController = new TransferController(storage);
    List<Account> heaton = storage.findByUser("heaton");

    TransactionAccount fromAccount = new TransactionAccount(heaton.get(0).getNumber(), heaton.get(0).getBalances().get(0).getCurrency());
    TransactionAccount toAccount = new TransactionAccount(heaton.get(1).getNumber(), heaton.get(1).getBalances().get(0).getCurrency());

    Transaction transaction = new Transaction(fromAccount, toAccount, new BigDecimal(500));
    transferController.transfer(transaction);

    assertThat(heaton.get(0).getBalances().get(0).getAmount(), is(new BigDecimal(99500)));
    assertThat(heaton.get(1).getBalances().get(0).getAmount(), is(new BigDecimal(200500)));
  }
}


