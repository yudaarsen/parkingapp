package ru.ikbo1018.app.data.row_mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet resultSet, int i) throws SQLException {
        Image result = new Image();
        result.setId(resultSet.getInt("id"));
        result.setAppealId(resultSet.getInt("appeal_id"));
        result.setData(resultSet.getBytes("data"));
        return result;
    }
}
