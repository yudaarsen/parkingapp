package ru.ikbo1018.app.data.row_mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.Appeal;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppealRowMapper implements RowMapper<Appeal> {

    public Appeal mapRow(ResultSet resultSet, int i) throws SQLException, IllegalArgumentException {
        Appeal result = new Appeal();
        result.setId(resultSet.getInt("id"));
        result.setAccountId(resultSet.getInt("account_id"));
        result.setSendDate(resultSet.getDate("send_date"));
        result.setStatusId(resultSet.getInt("status"));
        result.setCheckDate(resultSet.getDate("check_date"));
        result.setOperatorId(resultSet.getInt("operator_id"));
        result.setAppealText(resultSet.getString("appeal_text"));
        result.setAnswerText(resultSet.getString("answer_text"));
        result.setTypeId(resultSet.getInt("type_id"));
        result.setAddress(resultSet.getString("address"));
        return result;
    }
}
