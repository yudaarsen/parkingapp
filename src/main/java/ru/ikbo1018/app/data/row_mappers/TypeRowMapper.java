package ru.ikbo1018.app.data.row_mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Type;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TypeRowMapper implements RowMapper<Type> {
    public Type mapRow(ResultSet resultSet, int i) throws SQLException {
        Type result = new Type();
        result.setId(resultSet.getInt("id"));
        result.setName(resultSet.getString("name"));
        result.setDescription(resultSet.getString("description"));
        return result;
    }
}
