package com.ita.edu.speakua.utils.jdbc.services;

import com.ita.edu.speakua.utils.jdbc.dao.RoleDAO;
import com.ita.edu.speakua.utils.jdbc.entity.RoleEntity;

public class RoleService {
    private final RoleDAO roleDAO;

    public RoleService() {
        roleDAO = new RoleDAO();
    }

    public RoleEntity getRole(long id) {
        return roleDAO.getRole(id);
    }

    public String getRoleNameWhereUserId(long id) {
        return roleDAO.selectRoleWhereUserId(id).getName();
    }
}