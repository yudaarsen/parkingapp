package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Status;

import java.util.List;

@Component
public class StatusRepositoryImpl implements StatusRepository{

    private static final String SQL_GET_BY_ID = "SELECT * FROM status WHERE id = ?";

    @Autowired
    private RowMapper<Status> rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Status getById(int id) throws IllegalArgumentException {
        List<Status> result = jdbcTemplate.query(SQL_GET_BY_ID, rowMapper, id);
        if(result.isEmpty())
            throw new IllegalArgumentException("Status with the specified id does not exist");
        return result.get(0);
    }
}
