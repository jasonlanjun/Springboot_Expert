package com.junlan.repository;

import com.junlan.domain.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    @Query(value = "SELECT * FROM STOCK_ENTITY WHERE stock_barcode = ?1", nativeQuery = true)
	StockEntity findStockByBarcode(String stockBarcode);

}
