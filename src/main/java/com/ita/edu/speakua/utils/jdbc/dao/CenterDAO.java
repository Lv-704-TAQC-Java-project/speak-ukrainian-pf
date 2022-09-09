package com.ita.edu.speakua.utils.jdbc.dao;

import com.ita.edu.speakua.utils.jdbc.entity.CenterEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CenterDAO {
    public List<CenterEntity> selectAllCenters() {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(CenterEntity.SELECT_ALL_CENTERS);
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return CenterEntity.parseRows(rows);
    }

    public CenterEntity selectCenterById(long id) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(CenterEntity.SELECT_CENTER_BY_ID, id));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return CenterEntity.parseRows(rows).get(0);
    }

    public List<CenterEntity> selectCenters(String city, String orderBy, boolean desc, long limit) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(CenterEntity.SELECT_CENTERS_FROM_CITY_ORDERED_AND_SORTED_WITH_LIMIT, city, orderBy, desc ? "DESC" : "ASC", limit));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return CenterEntity.parseRows(rows);
    }
}