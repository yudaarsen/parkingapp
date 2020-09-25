package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.account.Account;

public interface AccountRepository {
    Account getByEmail(String email) throws IllegalArgumentException;
    Account getById(String id) throws IllegalArgumentException;
    void createAccount(Account account) throws IllegalArgumentException;
    void updateAccount(Account account) throws IllegalArgumentException;
}
