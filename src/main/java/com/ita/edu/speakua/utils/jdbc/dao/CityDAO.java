package com.ita.edu.speakua.utils.jdbc.dao;

import com.ita.edu.speakua.utils.jdbc.entity.CityEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CityDAO {
    public List<CityEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(CityEntity.SELLEC_ALL);
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return CityEntity.parseRows(rows);
    }
    public CityEntity selectById(long id) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(CityEntity.SELLEC_BY_ID, id));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return CityEntity.parseRows(rows).get(0);
    }
}
