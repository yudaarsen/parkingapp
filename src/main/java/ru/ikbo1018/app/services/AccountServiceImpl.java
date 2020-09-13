package ru.ikbo1018.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.data.AccountRepository;
import ru.ikbo1018.app.models.account.Account;

@Component
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;

    public void createAccount(Account account) {
       repository.createAccount(account);
    }
}