package com.ita.edu.speakua.utils.jdbc.services;

import com.ita.edu.speakua.utils.jdbc.dao.RoleDAO;

public class RoleService {
    private final RoleDAO roleDAO;

    public RoleService() {
        roleDAO = new RoleDAO();
    }

    public String getRoleNameWhereUserId(long id) {
        return roleDAO.selectRoleWhereUserId(id).getName();
    }
}