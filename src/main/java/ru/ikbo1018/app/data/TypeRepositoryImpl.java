package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Type;

import java.util.List;

@Component
public class TypeRepositoryImpl implements TypeRepository{

    private static final String SQL_GET_BY_ID = "SELECT * FROM type WHERE id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM type";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Type> rowMapper;

    public Type getById(int id) throws IllegalArgumentException {
        List<Type> result = jdbcTemplate.query(SQL_GET_BY_ID, rowMapper, id);
        if(result.isEmpty())
            throw new IllegalArgumentException("Type with the specified id does not exists");
        return result.get(0);
    }

    @Override
    public List<Type> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, rowMapper);
    }
}
