package com.junlan.service;

import com.junlan.domain.UserRolesEntity;
import com.junlan.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesServiceImpl implements UserRolesServiceInt {

    @Autowired
    private UserRolesRepository roleRepository;

    public void saveUserRole(UserRolesEntity userRole) {
        roleRepository.save(userRole);
    }

    public UserRolesEntity findRoleByUser(String userName) {
        return roleRepository.findRoleByUser(userName);
    }

    public List<UserRolesEntity> findAll() {
        return roleRepository.findAll();
    }

}
