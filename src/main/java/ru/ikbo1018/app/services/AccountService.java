package ru.ikbo1018.app.services;

import ru.ikbo1018.app.models.account.Account;

public interface AccountService {
    void createAccount(Account account);
    Account login(String email, String password) throws IllegalArgumentException;
    Account getAccountById(int id) throws IllegalArgumentException;
}
