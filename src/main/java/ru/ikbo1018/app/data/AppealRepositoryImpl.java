package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Appeal;

import java.util.List;

@Component
public class AppealRepositoryImpl implements AppealRepository{

    private static final String SQL_APPEALS_BY_ACCOUNT_ID = "SELECT * FROM appeal WHERE account_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Appeal> rowMapper;

    public List<Appeal> getAccountAppealsById(int accountId) throws IllegalArgumentException {
       return jdbcTemplate.query(SQL_APPEALS_BY_ACCOUNT_ID, rowMapper, accountId);
    }
}
