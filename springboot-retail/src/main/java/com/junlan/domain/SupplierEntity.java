package com.junlan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String supplierName;

    private String supplierCode;

    private String supplierEmail;

    private String supplierPhone;

    private String supplierAddress;

    private String supplierCity;

    private String supplierState;

    private String supplierCountry;

    private String supplierZipcode;

    private String supplierStatus;

    public SupplierEntity(SupplierEntity supplier) {
        super();
        this.id = supplier.getId();
        this.supplierName = supplier.getSupplierName();
        this.supplierCode = supplier.getSupplierCode();
        this.supplierEmail = supplier.getSupplierEmail();
        this.supplierPhone = supplier.getSupplierPhone();
        this.supplierAddress = supplier.getSupplierAddress();
        this.supplierCity = supplier.getSupplierCity();
        this.supplierState = supplier.getSupplierState();
        this.supplierCountry = supplier.getSupplierCountry();
        this.supplierZipcode = supplier.getSupplierZipcode();
        this.supplierStatus = supplier.getSupplierStatus();
    }

    public SupplierEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
    }

    public String getSupplierCountry() {
        return supplierCountry;
    }

    public void setSupplierCountry(String supplierCountry) {
        this.supplierCountry = supplierCountry;
    }

    public String getSupplierZipcode() {
        return supplierZipcode;
    }

    public void setSupplierZipcode(String supplierZipcode) {
        this.supplierZipcode = supplierZipcode;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }


}
