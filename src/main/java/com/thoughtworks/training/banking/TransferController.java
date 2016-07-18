package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.accounts.AccountStorage;
import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Balance;
import com.thoughtworks.training.banking.model.Transaction;
import com.thoughtworks.training.banking.model.TransactionAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by yqin on 7/18/16.
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {

  private final AccountStorage accountStorage;

  @Autowired
  public TransferController(AccountStorage accountStorage) {
    this.accountStorage = accountStorage;
  }

  @RequestMapping(method = RequestMethod.POST)
  public void transfer(@RequestBody Transaction transaction) {
    TransactionAccount transactionFrom = transaction.getFrom();
    Account fromAccount = accountStorage.findByAccountNumber(transactionFrom.getAccountNumber());
    TransactionAccount transactionTo = transaction.getTo();
    Account toAccount = accountStorage.findByAccountNumber(transactionTo.getAccountNumber());

    List<Balance> fromAccountBalances = fromAccount.getBalances();
    Optional<Balance> fromBalance = fromAccountBalances
            .stream()
            .filter(balance ->
                balance.getCurrency().equals(transactionFrom.getCurrency())).findFirst();

    List<Balance> toAccountBalances = toAccount.getBalances();
    Optional<Balance> toBalance = toAccountBalances
            .stream()
            .filter(balance ->
                balance.getCurrency().equals(transactionTo.getCurrency())).findFirst();

    if (fromBalance.isPresent() && toBalance.isPresent()) {
      Balance balance = fromBalance.get();
      fromAccountBalances.set(fromAccountBalances.indexOf(balance), new Balance(fromAccount.getId(), balance.getCurrency(), balance.getAmount().subtract(transaction.getAmount())));
      Balance balance1 = toBalance.get();
      toAccountBalances.set(toAccountBalances.indexOf(balance1), new Balance(toAccount.getId(), balance1.getCurrency(), balance1.getAmount().add(transaction.getAmount())));
    }
  }

}
