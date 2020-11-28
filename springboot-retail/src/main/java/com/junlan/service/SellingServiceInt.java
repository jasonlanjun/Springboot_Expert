package com.junlan.service;

import com.junlan.domain.SellingEntity;

import java.util.List;

public interface SellingServiceInt {

    List<SellingEntity> findAll();

    void saveSalesRecord(SellingEntity salesRecord);

    SellingEntity getSalesRecordById(Long id);

}
