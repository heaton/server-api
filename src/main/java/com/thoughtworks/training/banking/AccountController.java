package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.common.IdGenerator;
import com.thoughtworks.training.banking.model.Account;
import com.thoughtworks.training.banking.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yqin on 7/18/16.
 */
@RestController
@RequestMapping("/{userName}/accounts")
public class AccountController {
    private final IdGenerator idGenerator;

    private final Map<Long, Account> accounts;

    private void fillFakeAccounts() {
        Account heaton1 = new Account(1L, "1001", "Heaton", "heaton");
        heaton1.addBalance(new Balance(heaton1.getId(), "cny", new BigDecimal(100000)));
        heaton1.addBalance(new Balance(heaton1.getId(), "usd", new BigDecimal(30000)));
        Account heaton2 = new Account(2L, "2001", "Heaton", "heaton");
        heaton2.addBalance(new Balance(heaton2.getId(), "cny", new BigDecimal(200000)));
        Account qinyu = new Account(3L, "1002", "Qinyu", "qinyu");
        qinyu.addBalance(new Balance(qinyu.getId(), "cny", new BigDecimal(80000)));
        qinyu.addBalance(new Balance(qinyu.getId(), "usd", new BigDecimal(0)));

        accounts.put(heaton1.getId(), heaton1);
        accounts.put(heaton2.getId(), heaton2);
        accounts.put(qinyu.getId(), qinyu);
    }

    @Autowired
    public AccountController(IdGenerator idGenerator) {
        accounts = new HashMap<>();
        this.idGenerator = idGenerator;
        fillFakeAccounts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestParam("name") String name, @PathVariable String userName) {
        Account account = new Account(idGenerator.nextId(), null, name, userName);
        accounts.put(account.getId(), account);
        return account;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getAccountByUserName(@PathVariable String userName) {
        return accounts.values().stream().filter(account -> account.getUserName().equals(userName)).collect(Collectors.toList());
    }
}
