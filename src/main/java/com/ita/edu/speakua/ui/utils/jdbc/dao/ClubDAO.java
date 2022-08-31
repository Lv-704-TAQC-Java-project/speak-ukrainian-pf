package com.ita.edu.speakua.ui.utils.jdbc.dao;

import com.ita.edu.speakua.ui.utils.jdbc.entity.ClubEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClubDAO {

    public List<ClubEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(ClubEntity.SELECT_ALL);
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }

    public ClubEntity selectById(long id) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_BY_ID, id));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return ClubEntity.parseRows(rows).get(0);
    }

    public List<ClubEntity> selectAllOrderByRatingIdAscLimit(long id) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_ALL_ORDER_BY_RATING_ID_ASC_LIMIT, id));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }

    public List<ClubEntity> selectAllOrderByRatingIdDescLimit(long id) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_ALL_ORDER_BY_RATING_ID_DESC_LIMIT, id));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }

//    public List<ClubEntity> selectSixNamesAscending() {
//        Statement statement = ManagerDAO.getInstance().getStatement();
//        List<List<String>> rows;
//        try {
//            ResultSet resultSet = statement.executeQuery(ClubEntity.SELECT_SIX_NAMES_ASC);
//            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ManagerDAO.getInstance().closeStatement(statement);
//        return ClubEntity.parseNameRows(rows);
//    }
//
//    public List<ClubEntity> selectSixNamesDescending() {
//        Statement statement = ManagerDAO.getInstance().getStatement();
//        List<List<String>> rows;
//        try {
//            ResultSet resultSet = statement.executeQuery(ClubEntity.SELECT_SIX_NAMES_DESC);
//            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ManagerDAO.getInstance().closeStatement(statement);
//        return ClubEntity.parseNameRows(rows);
//    }
//
//    public List<ClubEntity> selectName() {
//        Statement statement = ManagerDAO.getInstance().getStatement();
//        List<List<String>> rows;
//        try {
//            ResultSet resultSet = statement.executeQuery(ClubEntity.SELECT_NAME);
//            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ManagerDAO.getInstance().closeStatement(statement);
//        return ClubEntity.parseNameRows(rows);
//    }
//
//    public List<ClubEntity> selectNameWhereName(String name) {
//        Statement statement = ManagerDAO.getInstance().getStatement();
//        List<List<String>> rows;
//        try {
//            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_NAME_WHERE_NAME_LIKE, name));
//            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ManagerDAO.getInstance().closeStatement(statement);
//        return ClubEntity.parseNameRows(rows);
//    }
}