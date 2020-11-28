package com.junlan.service;

import com.junlan.domain.SupplierEntity;
import com.junlan.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierServiceInt {

    @Autowired
    private SupplierRepository supplierRepository;

    public void saveSupplier(SupplierEntity supplier) {
        supplierRepository.save(supplier);
    }

    public List<SupplierEntity> findAll() {
        return supplierRepository.findAll();
    }

    public void deleteSupplier(Long id) {
        supplierRepository.delete(id);
    }

    public SupplierEntity getSupplierById(Long id) {
        return supplierRepository.findOne(id);
    }

    public SupplierEntity findSupplierByCode(String supplierCode) {
        return supplierRepository.findSupplierByCode(supplierCode);
    }

    public SupplierEntity findSupplierByEmail(String supplierEmail) {
        return supplierRepository.findSupplierByEmail(supplierEmail);
    }

    public SupplierEntity findSupplierByName(String supplierName) {
        return supplierRepository.findSupplierByName(supplierName);
    }

    public SupplierEntity findOne(Long id) {
        return supplierRepository.findOne(id);
    }
}
