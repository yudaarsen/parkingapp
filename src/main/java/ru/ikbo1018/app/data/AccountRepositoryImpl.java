package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.ikbo1018.app.models.account.Account;

@Component
public class AccountRepositoryImpl implements AccountRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_BY_EMAIL = "SELECT * FROM account WHERE email = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM account WHERE id = ?";
    private static final String SQL_CREATE = "INSERT INTO account VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";

    public Account getByEmail(String email) throws IllegalArgumentException {
        Account result = jdbcTemplate.queryForObject(SQL_GET_BY_EMAIL, new Object[] {email}, Account.class);
        if(result == null) {
            throw new IllegalArgumentException("Account with the specified email not found");
        }
        return result;
    }

    public Account getById(String id) throws IllegalArgumentException {
        Account result = jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] {id}, Account.class);
        if(result == null) {
            throw new IllegalArgumentException("Account with the specified id not found");
        }
        return result;
    }

    public void createAccount(Account account) {
        jdbcTemplate.update(SQL_CREATE, account.getFirstName(), account.getLastName(), account.getMidName(),
                account.getEmail(), account.getPassword());
    }

    public void updateAccount(Account account) {

    }
}
