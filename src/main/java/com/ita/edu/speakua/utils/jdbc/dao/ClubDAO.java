package com.ita.edu.speakua.utils.jdbc.dao;

import com.ita.edu.speakua.utils.jdbc.entity.ClubEntity;

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
        ManagerDAO.closeStatement(statement);
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
        ManagerDAO.closeStatement(statement);
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
        ManagerDAO.closeStatement(statement);
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
        ManagerDAO.closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }

    public List<ClubEntity> selectAllOrderByName(boolean desc, int limit) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_ALL_ORDER_BY_NAME_LIMIT,
                    desc ? "DESC" : "ASC", limit));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }

    public List<ClubEntity> selectNameWhereNameLike(String name) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_ALL_WHERE_NAME_LIKE, name));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }

    public List<ClubEntity> selectAllWhereCity(String city) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_ALL_WHERE_CITY, city));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }

    public List<ClubEntity> selectClubsFromCityBySearchPhrase(String city, String phrase, int limit) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(ClubEntity.SELECT_CLUBS_FROM_CITY_BY_SEARCH_PHRASE_WITH_LIMIT, city, phrase, phrase, limit));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return ClubEntity.parseRows(rows);
    }
}