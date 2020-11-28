package com.junlan.service;

import com.junlan.domain.BillEntity;
import com.junlan.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillServiceInt {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<BillEntity> findAll() {
        return billRepository.findAll();
    }

    @Override
    public void saveBill(BillEntity bill) {
        billRepository.save(bill);

    }

    @Override
    public BillEntity getBillById(Long id) {
        return billRepository.findOne(id);
    }

    @Override
    public Long getMaxBillNumber() {
        return billRepository.getMaxBillNumber();
    }
}
