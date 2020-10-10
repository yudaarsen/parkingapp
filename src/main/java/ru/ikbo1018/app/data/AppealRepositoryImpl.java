package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Appeal;

import java.util.List;
import java.util.logging.Logger;

@Component
public class AppealRepositoryImpl implements AppealRepository{

    private static final String SQL_APPEALS_BY_ACCOUNT_ID = "SELECT * FROM appeal WHERE account_id = ?";
    private static final String SQL_CREATE_APPEAL = "INSERT INTO appeal VALUES (DEFAULT, ?, DEFAULT, " +
            "?, DEFAULT, DEFAULT, ?, DEFAULT, ?, ?) RETURNING *";
    private static final String SQL_GET_APPEAL = "SELECT * FROM appeal WHERE id = ?";

    @Value("${appealDefaultStatus}")
    private int defaultStatus;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Appeal> rowMapper;

    public List<Appeal> getAccountAppealsById(int accountId) throws IllegalArgumentException {
       return jdbcTemplate.query(SQL_APPEALS_BY_ACCOUNT_ID, rowMapper, accountId);
    }

    @Override
    public Appeal create(final Appeal appeal) throws IllegalArgumentException {
        if(appeal == null)
            throw new IllegalArgumentException("Specified appeal object is null");
        return jdbcTemplate.query(SQL_CREATE_APPEAL, new Object[] {appeal.getAccountId(),
                        defaultStatus, appeal.getAppealText(), appeal.getTypeId(), appeal.getAddress()},
                        rowMapper).get(0);
    }

    @Override
    public Appeal getAppeal(int appealId) throws IllegalArgumentException {
        if(appealId <= 0)
            throw new IllegalArgumentException("Incorrect appeal id");
        try {
            return jdbcTemplate.query(SQL_GET_APPEAL, new Object[]{appealId}, rowMapper).get(0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Appeal with the specified id does not exist");
        }
    }
}
