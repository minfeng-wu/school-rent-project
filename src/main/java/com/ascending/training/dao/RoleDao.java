package com.ascending.training.dao;

import com.ascending.training.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);
    Role save(Role role);
    int delete(Role role);
    List<Role> findAllRoles();
}
