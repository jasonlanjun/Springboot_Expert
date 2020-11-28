package com.junlan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String stockProduct;

    private String stockCategory;

    private String stockBrand;

    private int stockQuantity;

    private String stockDate;

    private String stockStatus;

    private String stockBarcode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(String stockProduct) {
        this.stockProduct = stockProduct;
    }

    public String getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(String stockCategory) {
        this.stockCategory = stockCategory;
    }

    public String getStockBrand() {
        return stockBrand;
    }

    public void setStockBrand(String stockBrand) {
        this.stockBrand = stockBrand;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getStockBarcode() {
        return stockBarcode;
    }

    public void setStockBarcode(String stockBarcode) {
        this.stockBarcode = stockBarcode;
    }
}
