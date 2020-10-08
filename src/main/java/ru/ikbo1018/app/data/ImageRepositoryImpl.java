package ru.ikbo1018.app.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ImageRepositoryImpl implements ImageRepository{

    private static final String SQL_CREATE_IMAGE = "INSERT INTO image VALUES (DEFAULT, ?, ?) RETURNING *";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Image> rowMapper;

    @Override
    public Image create(final Image image){
        if(image == null)
            throw new IllegalArgumentException("Specified image does not exist");
        final Image result = new Image();
        jdbcTemplate.execute(SQL_CREATE_IMAGE, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                Connection connection = preparedStatement.getConnection();
                connection.setAutoCommit(false);

                preparedStatement.setInt(1, image.getAppealId());
                preparedStatement.setBytes(2, image.getData());

                connection.commit();
                connection.setAutoCommit(true);
                ResultSet resultSet =  preparedStatement.executeQuery();
                resultSet.next();
                result.setId(resultSet.getInt("id"));
                result.setAppealId(resultSet.getInt("appeal_id"));
                result.setData(resultSet.getBytes("data"));
                return true;
            }
        });
        return result;
    }
}
