package com.junlan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String poNumber;

    private String poProduct;

    private String poCategory;

    private String poBrand;

    private String poSupplier;

    private String poDeliveryDate;

    private String poDesc;

    private int poQuantity;

    private String poDate;

    private String poStatus = "Pending";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoProduct() {
        return poProduct;
    }

    public void setPoProduct(String poProduct) {
        this.poProduct = poProduct;
    }

    public String getPoCategory() {
        return poCategory;
    }

    public void setPoCategory(String poCategory) {
        this.poCategory = poCategory;
    }

    public String getPoBrand() {
        return poBrand;
    }

    public void setPoBrand(String poBrand) {
        this.poBrand = poBrand;
    }

    public String getPoSupplier() {
        return poSupplier;
    }

    public void setPoSupplier(String poSupplier) {
        this.poSupplier = poSupplier;
    }

    public String getPoDeliveryDate() {
        return poDeliveryDate;
    }

    public void setPoDeliveryDate(String poDeliveryDate) {
        this.poDeliveryDate = poDeliveryDate;
    }

    public String getPoDesc() {
        return poDesc;
    }

    public void setPoDesc(String poDesc) {
        this.poDesc = poDesc;
    }

    public int getPoQuantity() {
        return poQuantity;
    }

    public void setPoQuantity(int poQuantity) {
        this.poQuantity = poQuantity;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

}
