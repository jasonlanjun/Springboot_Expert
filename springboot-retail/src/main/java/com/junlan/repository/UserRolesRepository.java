package com.junlan.repository;

import com.junlan.domain.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {

    @Query(value = "SELECT * FROM USER_ROLES_ENTITY WHERE user_name = ?1", nativeQuery = true)
    UserRolesEntity findRoleByUser(String userName);
}
