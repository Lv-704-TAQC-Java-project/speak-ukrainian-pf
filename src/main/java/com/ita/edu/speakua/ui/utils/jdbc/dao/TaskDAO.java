package com.ita.edu.speakua.ui.utils.jdbc.dao;

import com.ita.edu.speakua.ui.utils.jdbc.entity.TaskEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TaskDAO {
    public List<TaskEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(TaskEntity.SELECT_ALL);
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return TaskEntity.parseRows(rows);
    }

    public List<TaskEntity> selectWhereName(String name) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(TaskEntity.SELECT_WHERE_NAME, name));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return TaskEntity.parseRows(rows);
    }

    public List<TaskEntity> selectLikeName(String name) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(TaskEntity.SELECT_LIKE_NAME, name));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.getInstance().closeStatement(statement);
        return TaskEntity.parseRows(rows);
    }
}
