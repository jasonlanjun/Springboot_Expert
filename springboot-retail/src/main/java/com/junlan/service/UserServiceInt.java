package com.junlan.service;

import com.junlan.domain.UserEntity;

import java.util.List;

public interface UserServiceInt {

    void saveUser(UserEntity user);

    UserEntity getUserById(Long id);

    List<UserEntity> findAll();

    void deleteUser(Long id);

    UserEntity findUserByEmail(String email);

    UserEntity findUserByResetToken(String token);

    Long findIdByUsername(String userName);

    void updateUserRole(Long id, String userRole);

    void updateUserDetails(Long id, String firstName, String lastName, String email, String contactNo,
                           String country, String state, String city, String street, String nationality, String zipcode);
}
