package com.junlan.service;

import com.junlan.domain.UserRolesEntity;

import java.util.List;

public interface UserRolesServiceInt {

    void saveUserRole(UserRolesEntity userRole);

    UserRolesEntity findRoleByUser(String userName);

    List<UserRolesEntity> findAll();
}
