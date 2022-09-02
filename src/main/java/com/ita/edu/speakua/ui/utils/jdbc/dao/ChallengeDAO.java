package com.ita.edu.speakua.ui.utils.jdbc.dao;

import com.ita.edu.speakua.ui.utils.jdbc.entity.ChallengeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ChallengeDAO {
    public List<ChallengeEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(ChallengeEntity.SELECT_ALL_CHALLENGES);
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return ChallengeEntity.parseRows(rows);
    }

    public ChallengeEntity selectById(long id) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ChallengeEntity.SELECT_BY_ID, id));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return ChallengeEntity.parseRows(rows).get(0);
    }
}