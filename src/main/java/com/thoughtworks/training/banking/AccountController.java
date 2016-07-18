package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.common.IdGenerator;
import com.thoughtworks.training.banking.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by yqin on 7/18/16.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final IdGenerator idGenerator;

    private final Map<Long, Account> accounts;

    @Autowired
    public AccountController(IdGenerator idGenerator) {
        accounts = new HashMap<>();
        this.idGenerator = idGenerator;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestParam("name") String name, @RequestParam("user") String userName) {
        Account account = new Account(idGenerator.nextId(), null, name, userName);
        accounts.put(account.getId(), account);
        return account;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getAccountByUserName(@RequestParam("user") String userName) {
        return accounts.values().stream().filter(new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                return account.getUserName().equals(userName);
            }
        }).collect(Collectors.toList());
    }
}
