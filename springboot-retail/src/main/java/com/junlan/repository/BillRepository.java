package com.junlan.repository;

import com.junlan.domain.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

    @Query(value = "SELECT MAX(bill_number) FROM BILL_ENTITY", nativeQuery = true)
	Long getMaxBillNumber();
}
