package com.junlan.service;

import com.junlan.domain.BillEntity;

import java.util.List;

public interface BillServiceInt {

    List<BillEntity> findAll();

    void saveBill(BillEntity bill);

    BillEntity getBillById(Long id);

    Long getMaxBillNumber();

}
