package ru.ikbo1018.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public boolean isAccountExists(String email) {
        try {
            repository.getByEmail(email);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Account login(String email, String password) throws IllegalArgumentException {
        Account account = repository.getByEmail(email);
        if(account.getPassword().equals(password))
            return account;
        else
            throw new IllegalArgumentException("Incorrect password");
    }

    public Account getAccountById(int id) throws IllegalArgumentException {
        return repository.getById(id);
    }

    @Override
    public void updateAccount(Account account) throws IllegalArgumentException {
        try {
            repository.updateAccount(account);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Unable to update specified account");
        }
    }
}
