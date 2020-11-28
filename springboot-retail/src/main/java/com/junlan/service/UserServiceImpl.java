package com.junlan.service;

import com.junlan.domain.UserEntity;
import com.junlan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInt {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findUserByResetToken(String token) {
        return userRepository.findByResetToken(token);
    }

    public void updateUserRole(Long id, String userRole) {
        userRepository.updateUserByRole(id, userRole);
    }

    public void updateUserDetails(Long id, String firstName, String lastName, String email, String contactNo,
                                  String country, String state, String city, String street, String nationality, String zipcode) {
        userRepository.updateUserDetails(id, firstName, lastName, email, contactNo, country, state, city, street,
                nationality, zipcode);
    }

    public Long findIdByUsername(String userName) {
        return userRepository.findIdByUsername(userName);
    }

}
