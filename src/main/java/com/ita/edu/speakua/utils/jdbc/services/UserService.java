package com.ita.edu.speakua.utils.jdbc.services;


import com.ita.edu.speakua.utils.jdbc.dao.UserDAO;
import com.ita.edu.speakua.utils.jdbc.entity.UserEntity;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public List<UserEntity> getUsers(String email, String firstName, String lastName) {
        return userDAO.selectUsers(email, firstName, lastName);
    }

    public long getTasksCount(String email, String firstName, String lastName) {
        return userDAO.getUsersCount(email, firstName, lastName);
    }

    public List<UserEntity> getUsersWhereId(long id) {
        return userDAO.selectUsersWhereId(id);
    }
}