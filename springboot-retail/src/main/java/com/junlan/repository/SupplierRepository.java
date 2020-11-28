package com.junlan.repository;

import com.junlan.domain.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

    @Query(value = "SELECT * FROM SUPPLIER_ENTITY WHERE supplier_code = ?1", nativeQuery = true)
    SupplierEntity findSupplierByCode(String supplierCode);

    @Query(value = "SELECT * FROM SUPPLIER_ENTITY WHERE supplier_email = ?1", nativeQuery = true)
    SupplierEntity findSupplierByEmail(String supplierEmail);

    @Query(value = "SELECT * FROM SUPPLIER_ENTITY WHERE supplier_name = ?1", nativeQuery = true)
    SupplierEntity findSupplierByName(String supplierName);

    @Query(value = "SELECT * FROM SUPPLIER_ENTITY ORDER BY supplier_name", nativeQuery = true)
    List<SupplierEntity> findAll();
}
