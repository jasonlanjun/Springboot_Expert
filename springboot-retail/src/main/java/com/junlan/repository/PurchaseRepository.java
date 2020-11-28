package com.junlan.repository;

import com.junlan.domain.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    @Query(value = "SELECT MAX(id) FROM PURCHASE_ENTITY", nativeQuery = true)
	Long findMaxId();
}
