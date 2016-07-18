package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.accounts.AccountStorage;
import com.thoughtworks.training.banking.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/{userName}/accounts")
public class AccountController {

  private final AccountStorage accountStorage;

  @Autowired
  public AccountController(AccountStorage accountStorage) {
    this.accountStorage = accountStorage;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<Account> getAccountByUserName(@PathVariable String userName) {
    return accountStorage.findByUser(userName);
  }

}
