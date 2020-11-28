package com.junlan.repository;

import com.junlan.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM USER_ENTITY WHERE email = ?1", nativeQuery = true)
    UserEntity findByEmail(String email);

    @Query(value = "SELECT ID FROM USER_ENTITY WHERE user_name = ?1", nativeQuery = true)
    Long findIdByUsername(String userName);

    @Query(value = "SELECT * FROM USER_ENTITY WHERE reset_token = ?1", nativeQuery = true)
    UserEntity findByResetToken(String resetToken);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.userRole = :userRole WHERE u.id = :id")
    int updateUserByRole(@Param("id") Long id, @Param("userRole") String userRole);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email, u.contactNo = :contactNo, u.country = :country, u.state = :state, u.city = :city, u.street = :street, u.nationality = :nationality, u.zipcode = :zipcode WHERE u.id = :id")
    int updateUserDetails(@Param("id") Long id, @Param("firstName") String firstName,
                          @Param("lastName") String lastName, @Param("email") String email, @Param("contactNo") String contactNo,
                          @Param("country") String country, @Param("state") String state, @Param("city") String city,
                          @Param("street") String street, @Param("nationality") String nationality, @Param("zipcode") String zipcode);
}
