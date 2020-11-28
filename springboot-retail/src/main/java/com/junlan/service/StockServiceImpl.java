package com.junlan.service;

import com.junlan.domain.StockEntity;
import com.junlan.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockServiceInt {

    @Autowired
    private StockRepository stockRepository;

    public void saveStock(StockEntity stockOrder) {
        stockRepository.save(stockOrder);
    }

    public List<StockEntity> findAll() {
        return stockRepository.findAll();
    }

    public void deleteStock(Long id) {
        stockRepository.delete(id);
    }

    public StockEntity getStockById(Long id) {
        return stockRepository.findOne(id);
    }

    public StockEntity findStockByBarcode(String stockBarcode) {
        return stockRepository.findStockByBarcode(stockBarcode);
    }
}
