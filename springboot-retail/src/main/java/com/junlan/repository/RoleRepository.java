package com.junlan.repository;

import com.junlan.domain.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRolesEntity, Long> {

}
