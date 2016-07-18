package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.accounts.AccountStorage;
import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Balance;
import com.thoughtworks.training.banking.model.Transaction;
import com.thoughtworks.training.banking.model.TransactionAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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

    List<Balance> fromBalances = fromAccount.getBalances();
    Balance oldFromBalance = accountStorage.findAccountBalanceByCurrency(fromAccount, transactionFrom.getCurrency());

    List<Balance> toBalances = toAccount.getBalances();
    Balance oldToBalance = accountStorage.findAccountBalanceByCurrency(toAccount, transactionTo.getCurrency());

    Balance newFromBalance = new Balance(fromAccount.getId(), oldFromBalance.getCurrency(), oldFromBalance.getAmount().subtract(transaction.getAmount()));
    fromBalances.set(fromBalances.indexOf(oldFromBalance), newFromBalance);
    Balance newToBalance = new Balance(toAccount.getId(), oldToBalance.getCurrency(), oldToBalance.getAmount().add(transaction.getAmount()));
    toBalances.set(toBalances.indexOf(oldToBalance), newToBalance);
  }


}
