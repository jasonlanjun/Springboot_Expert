package com.junlan.service;

import com.junlan.domain.SellingEntity;
import com.junlan.repository.SellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellingServiceImpl implements SellingServiceInt {

    @Autowired
    private SellingRepository sellingRepository;

    @Override
    public List<SellingEntity> findAll() {
        return sellingRepository.findAll();
    }

    @Override
    public void saveSalesRecord(SellingEntity sellRecord) {
        sellingRepository.save(sellRecord);
    }

    @Override
    public SellingEntity getSalesRecordById(Long id) {
        return sellingRepository.findOne(id);
    }
}
