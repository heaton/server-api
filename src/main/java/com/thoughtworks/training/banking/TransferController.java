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
    TransactionAccount transactionFromAccount = transaction.getFromAccount();
    Optional<Account> fromAccount = accountStorage.findByAccountNumber(transactionFromAccount.getAccountNumber());
    TransactionAccount transactionToAccount = transaction.getToAccount();
    Optional<Account> toAccount = accountStorage.findByAccountNumber(transactionToAccount.getAccountNumber());

    Optional<Balance> fromBalance = fromAccount.flatMap(account ->
        account.getBalances()
            .stream()
            .filter(balance ->
                balance.getCurrency().equals(transactionFromAccount.getCurrency())).findFirst());
    Optional<Balance> toBalance = toAccount.flatMap(account ->
        account.getBalances()
            .stream()
            .filter(balance ->
                balance.getCurrency().equals(transactionToAccount.getCurrency())).findFirst());

    if (fromBalance.isPresent() && toBalance.isPresent()) {
      fromAccount.get().getBalances().set(0, new Balance(fromAccount.get().getId(), fromBalance.get().getCurrency(), fromBalance.get().getAmount().subtract(transaction.getAmount())));
      toAccount.get().getBalances().set(0, new Balance(toAccount.get().getId(), toBalance.get().getCurrency(), toBalance.get().getAmount().add(transaction.getAmount())));
    }
  }

}
