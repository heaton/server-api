package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.accounts.AccountStorage;
import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Balance;
import com.thoughtworks.training.banking.model.Transaction;
import com.thoughtworks.training.banking.model.TransactionAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    updateAccount(transaction.getFrom(), transaction.getAmount().negate());
    updateAccount(transaction.getTo(), transaction.getAmount());
  }

  private void updateAccount(TransactionAccount account, BigDecimal amount) {
    accountStorage.updateAccount(account.getAccountNumber(), account.getCurrency(), amount);
  }


}
