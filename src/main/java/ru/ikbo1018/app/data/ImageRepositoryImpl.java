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
import java.util.List;

@Component
public class ImageRepositoryImpl implements ImageRepository{

    private static final String SQL_CREATE_IMAGE = "INSERT INTO image VALUES (DEFAULT, ?, ?) RETURNING *";
    private static final String SQL_GET_IMAGES = "SELECT * FROM image WHERE appeal_id = ?";
    private static final String SQL_GET_IMAGE = "SELECT * FROM image WHERE id = ?";

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

    @Override
    public List<Image> getAppealImages(int appealId) throws IllegalArgumentException {
        if(appealId <= 0)
            throw new IllegalArgumentException("Incorrect appeal id");
        try {
            return jdbcTemplate.query(SQL_GET_IMAGES, new Object[] {appealId}, rowMapper);
        } catch (Exception e) {
            throw new IllegalArgumentException("Images for specified appeal are not found");
        }
    }

    @Override
    public Image getImage(int imageId) throws IllegalArgumentException {
        if(imageId <= 0)
            throw new IllegalArgumentException("Incorrect image id");
        try {
            return jdbcTemplate.query(SQL_GET_IMAGE, new Object[] {imageId}, rowMapper).get(0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Image with specified id does not exist");
        }
    }
}
