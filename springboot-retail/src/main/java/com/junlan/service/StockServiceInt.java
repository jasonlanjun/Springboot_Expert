package com.junlan.service;

import com.junlan.domain.StockEntity;

import java.util.List;

public interface StockServiceInt {

    void saveStock(StockEntity user);

    List<StockEntity> findAll();

    void deleteStock(Long id);

    StockEntity getStockById(Long id);

    StockEntity findStockByBarcode(String stockBarcode);

}
