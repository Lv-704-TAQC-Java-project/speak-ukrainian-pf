package com.ita.edu.speakua.utils.jdbc.dao;

import com.ita.edu.speakua.utils.jdbc.entity.TaskEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static java.lang.String.format;

public class TaskDAO {
    public List<TaskEntity> selectAllTasks() {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(TaskEntity.SELECT_ALL_TASKS);
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return TaskEntity.parseRows(rows);
    }

    public List<TaskEntity> selectTasks(long challengeId) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(format(TaskEntity.SELECT_TASKS_FROM_CHALLENGE, challengeId));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return TaskEntity.parseRows(rows);
    }

    public List<TaskEntity> selectTasks(String name) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(format(TaskEntity.SELECT_TASKS_WITH_NAME, name));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return TaskEntity.parseRows(rows);
    }

    public List<TaskEntity> selectTasks(String name, String orderBy, boolean desc) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(format(TaskEntity.SELECT_TASKS_WITH_NAME_ORDERED_AND_SORTED, name, orderBy, desc ? "DESC" : "ASC"));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return TaskEntity.parseRows(rows);
    }


    public long getTasksCount(String name) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(format(TaskEntity.COUNT_TASKS_WITH_NAME, name));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return Long.parseLong(rows.get(0).get(0));
    }

    public long getTasksMaxId() {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(TaskEntity.MAX_TASKS_ID);
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return Long.parseLong(rows.get(0).get(0));
    }

    public TaskEntity selectTask(long taskId) {
        Statement statement = ManagerDAO.getInstance().getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(format(TaskEntity.SELECT_TASK_WHERE_ID, taskId));
            rows = ManagerDAO.getInstance().parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ManagerDAO.closeStatement(statement);
        return TaskEntity.parseRow(rows.get(0));
    }
}