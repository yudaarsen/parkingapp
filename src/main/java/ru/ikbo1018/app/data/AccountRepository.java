package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.account.Account;

public interface AccountRepository {
    Account getByEmail(String email);
    Account getById(String id);
    void createAccount(Account account);
    void updateAccount(Account account);
}
