package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.account.Account;

import java.util.List;

@Component
public class AccountRepositoryImpl implements AccountRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Account> rowMapper;

    private static final String SQL_GET_BY_EMAIL = "SELECT * FROM account WHERE email = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM account WHERE id = ?";
    private static final String SQL_CREATE = "INSERT INTO account VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
    private static final String SQL_UPDATE = "UPDATE account SET first_name = ?, last_name = ?, mid_name = ?," +
            "password = ?, account_role = ? WHERE id = ?";

    public Account getByEmail(String email) throws IllegalArgumentException {
        List<Account> result = jdbcTemplate.query(SQL_GET_BY_EMAIL, rowMapper, email);
        if(result.isEmpty()) {
            throw new IllegalArgumentException("Account with the specified email not found");
        }
        return result.get(0);
    }

    public Account getById(String id) throws IllegalArgumentException {
        List<Account> result = jdbcTemplate.query(SQL_GET_BY_ID, rowMapper, id);
        if(result.isEmpty()) {
            throw new IllegalArgumentException("Account with the specified id not found");
        }
        return result.get(0);
    }

    public void createAccount(Account account) {
        jdbcTemplate.update(SQL_CREATE, account.getFirstName(), account.getLastName(), account.getMidName(),
                account.getEmail(), account.getPassword());
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update(SQL_UPDATE, account.getFirstName(), account.getLastName(), account.getMidName(),
                account.getPassword(), account.getAccountRole().getId());
    }
}
