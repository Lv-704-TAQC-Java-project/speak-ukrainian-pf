package com.ita.edu.speakua.utils.jdbc.dao;

import com.ita.edu.speakua.utils.jdbc.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAO {
    public UserEntity selectUser(String email) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UserEntity.SELECT_USER_BY_EMAIL, email));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return UserEntity.parseRows(rows).get(0);
    }

    public List<UserEntity> selectUsers(String email, String firstName, String lastName) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UserEntity.SELECT_USER_BY_EMAIL_AND_FULL_NAME, email, firstName, lastName));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return UserEntity.parseRows(rows);
    }

    public long getUsersCount(String email, String firstName, String lastName) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UserEntity.COUNT_USERS_WITH_EMAIL_AND_FULL_NAME, email, firstName, lastName));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return Long.parseLong(rows.get(0).get(0));
    }

    public UserEntity selectUsersWhereId(long id) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UserEntity.SELECT_ALL_WHERE_ID, id));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return UserEntity.parseRows(rows).get(0);
    }
}