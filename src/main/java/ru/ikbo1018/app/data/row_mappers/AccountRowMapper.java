package ru.ikbo1018.app.data.row_mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.data.AccountRoleRepository;
import ru.ikbo1018.app.models.AccountRole;
import ru.ikbo1018.app.models.account.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountRowMapper implements RowMapper<Account> {

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setFirstName(resultSet.getString("first_name"));
        account.setLastName(resultSet.getString("last_name"));
        account.setMidName(resultSet.getString("mid_name"));
        account.setEmail(resultSet.getString("email"));
        account.setPassword(resultSet.getString("password"));
        account.setRegDate(resultSet.getDate("reg_date"));
        AccountRole accountRole = new AccountRole();
        try {
            accountRole = accountRoleRepository.getAccountRoleById(resultSet.getInt("account_role"));
        } catch (IllegalArgumentException e) {}
        account.setAccountRole(accountRole);
        return account;
    }
}
