package ru.ikbo1018.app.data.row_mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StatusRowMapper implements RowMapper<Status> {
    public Status mapRow(ResultSet resultSet, int i) throws SQLException {
        Status result = new Status();
        result.setId(resultSet.getInt("id"));
        result.setDescription(resultSet.getString("description"));
        return result;
    }
}
