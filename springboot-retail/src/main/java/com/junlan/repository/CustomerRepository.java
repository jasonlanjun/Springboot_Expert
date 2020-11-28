package com.junlan.repository;

import com.junlan.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT * FROM CUSTOMER_ENTITY WHERE customer_mobile = ?1", nativeQuery = true)
    CustomerEntity findCustomerByMobile(String mobile);

}
