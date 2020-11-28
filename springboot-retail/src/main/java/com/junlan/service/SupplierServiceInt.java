package com.junlan.service;

import com.junlan.domain.SupplierEntity;

import java.util.List;

public interface SupplierServiceInt {

    void saveSupplier(SupplierEntity user);

    List<SupplierEntity> findAll();

    void deleteSupplier(Long id);

    SupplierEntity getSupplierById(Long id);

    SupplierEntity findSupplierByCode(String supplierCode);

    SupplierEntity findSupplierByEmail(String supplierEmail);

    SupplierEntity findSupplierByName(String supplierName);

    SupplierEntity findOne(Long id);
}
