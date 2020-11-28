package com.junlan.repository;

import com.junlan.domain.SellingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingRepository extends JpaRepository<SellingEntity, Long> {

    @Query(value = "SELECT MAX(bill_number) FROM BILL_ENTITY", nativeQuery = true)
    Long findMaxBillNumber();
}
