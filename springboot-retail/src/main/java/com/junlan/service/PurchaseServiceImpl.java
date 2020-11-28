package com.junlan.service;

import com.junlan.domain.PurchaseEntity;
import com.junlan.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseServiceInt {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public void savePurchase(PurchaseEntity purchaseOrder) {
        purchaseRepository.save(purchaseOrder);
    }

    public List<PurchaseEntity> findAll() {
        return purchaseRepository.findAll();
    }

    public void deletePurchase(Long id) {
        purchaseRepository.delete(id);
    }

    public PurchaseEntity getPurchaseById(Long id) {
        return purchaseRepository.findOne(id);
    }

    public Long findMaxIdForProductEntity() {
        return purchaseRepository.findMaxId();
    }
}
