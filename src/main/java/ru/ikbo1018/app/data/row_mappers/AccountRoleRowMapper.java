package ru.ikbo1018.app.data.row_mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.AccountRole;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountRoleRowMapper implements RowMapper<AccountRole> {
    public AccountRole mapRow(ResultSet resultSet, int i) throws SQLException {
        AccountRole accountRole = new AccountRole();
        accountRole.setId(resultSet.getInt("id"));
        accountRole.setName(resultSet.getString("name"));
        return accountRole;
    }
}
