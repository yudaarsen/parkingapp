package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.account.Account;

public interface AccountRepository {
    Account getByEmail(final String email) throws IllegalArgumentException;
    Account getById(int id) throws IllegalArgumentException;
    void createAccount(final Account account) throws IllegalArgumentException;
    void updateAccount(final Account account) throws IllegalArgumentException;
}
