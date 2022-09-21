package com.ita.edu.speakua.utils.jdbc.services;

import com.ita.edu.speakua.utils.jdbc.dao.RoleDAO;
import com.ita.edu.speakua.utils.jdbc.dao.UserDAO;
import com.ita.edu.speakua.utils.jdbc.dto.UserJoinRoleDTO;
import com.ita.edu.speakua.utils.jdbc.entity.UserEntity;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;


    public UserService() {
        userDAO = new UserDAO();
        roleDAO = new RoleDAO();
    }

    public UserEntity getUser(String email) {
        return userDAO.selectUser(email);
    }

    public List<UserEntity> getUsers(String email, String firstName, String lastName) {
        return userDAO.selectUsers(email, firstName, lastName);
    }

    public long getTasksCount(String email, String firstName, String lastName) {
        return userDAO.getUsersCount(email, firstName, lastName);
    }

    public UserEntity getUserWhereId(long id) {
        return userDAO.selectUsersWhereId(id);
    }

    public UserJoinRoleDTO getUserJoinRoleDTO(String email) {
        UserEntity user = userDAO.selectUser(email);
        UserJoinRoleDTO userJoinRoleDTO = new UserJoinRoleDTO(user);
        userJoinRoleDTO.setRole(roleDAO.getRole(user.getRoleId()));
        return userJoinRoleDTO;
    }
}