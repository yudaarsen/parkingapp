package ru.ikbo1018.app.data.row_mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.data.AccountRepository;
import ru.ikbo1018.app.data.StatusRepository;
import ru.ikbo1018.app.data.TypeRepository;
import ru.ikbo1018.app.models.Appeal;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppealRowMapper implements RowMapper<Appeal> {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TypeRepository typeRepository;

    public Appeal mapRow(ResultSet resultSet, int i) throws SQLException, IllegalArgumentException {
        Appeal result = new Appeal();
        result.setId(resultSet.getInt("id"));
        result.setAccount(accountRepository.getById(resultSet.getInt("account_id")));
        result.setSendDate(resultSet.getDate("send_date"));
        result.setStatus(statusRepository.getById(resultSet.getInt("status")));
        result.setCheckDate(resultSet.getDate("check_date"));
        result.setOperator(accountRepository.getById(resultSet.getInt("operator_id")));
        result.setAppealText(resultSet.getString("appeal_text"));
        result.setAnswerText(resultSet.getString("answer_text"));
        result.setType(typeRepository.getById(resultSet.getInt("type_id")));
        result.setAddress(resultSet.getString("address"));
        return result;
    }
}
