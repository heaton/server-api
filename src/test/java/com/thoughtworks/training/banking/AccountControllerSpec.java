package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.common.IdGenerator;
import com.thoughtworks.training.banking.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Created by yqin on 7/18/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerSpec {

    @Mock
    private IdGenerator idGenerator;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void should_create_account() {
        when(idGenerator.nextId()).thenReturn(8L);

        Account account = accountController.createAccount("Yu's account", "qinyu");
        assertThat(account, is(notNullValue()));
        assertThat(account.getUserName(), is("qinyu"));
        assertThat(account.getName(), is("Yu's account"));
        assertThat(account.getId(), is(8L));
    }

    @Test
    public void should_get_account() {
        when(idGenerator.nextId()).thenReturn(8L);
        Account account = accountController.createAccount("Yu's account", "qinyu");
        List<Account> accountByUserName = accountController.getAccountByUserName("qinyu");
        assertThat(accountByUserName, is(notNullValue()));
        assertThat(accountByUserName, hasItem(is(account)));
    }

    @Test
    public void should_create_multiple_accounts_for_same_user() {
        when(idGenerator.nextId()).thenReturn(8L, 9L);
        Account account0 = accountController.createAccount("Yu's 1st account", "qinyu");
        Account account1 = accountController.createAccount("Yu's 2nd account", "qinyu");
        assertThat(account0, is(notNullValue()));
        assertThat(account0.getUserName(), is("qinyu"));
        assertThat(account0.getName(), is("Yu's 1st account"));
        assertThat(account0.getId(), is(8L));

        assertThat(account1, is(notNullValue()));
        assertThat(account1.getUserName(), is("qinyu"));
        assertThat(account1.getName(), is("Yu's 2nd account"));
        assertThat(account1.getId(), is(9L));
    }

    @Test
    public void should_get_multiple_accounts_for_user() {
        when(idGenerator.nextId()).thenReturn(8L, 9L, 10L);
        Account account0 = accountController.createAccount("Yu's 1st account", "qinyu");
        Account account1 = accountController.createAccount("Yu's 2nd account", "qinyu");
        Account account2 = accountController.createAccount("Heaton's account", "heaton");

        List<Account> accounts = accountController.getAccountByUserName("qinyu");
        assertThat(accounts.size(), is(2));
        assertThat(accounts, allOf(hasItems(account0, account1), not(hasItem(account2))));
    }

}
