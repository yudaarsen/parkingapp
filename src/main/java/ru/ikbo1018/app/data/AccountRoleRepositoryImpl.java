package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.AccountRole;

import java.util.List;

@Component
public class AccountRoleRepositoryImpl implements AccountRoleRepository{

    private static final String SQL_FIND_BY_ID = "SELECT * FROM role WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<AccountRole> rowMapper;

    public AccountRole getAccountRoleById(int id) throws IllegalArgumentException {
        List<AccountRole> result = jdbcTemplate.query(SQL_FIND_BY_ID, rowMapper, id);
        if(result.isEmpty())
            throw new IllegalArgumentException("AccountRole with the specified id does not exist");
        return result.get(0);
    }
}
