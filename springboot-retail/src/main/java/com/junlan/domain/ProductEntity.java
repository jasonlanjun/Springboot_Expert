package com.junlan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productName;

    private String productCode;

    private String productCategory;

    private String productBrand;

    private String productSerialNo;

    private String productBarcode;

    private String productDesc;

    private Double priceListing;

    private Double priceSelling;

    private Double priceDiscount;

    private String productStatus = "1";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSerialNo() {
        return productSerialNo;
    }

    public void setProductSerialNo(String productSerialNo) {
        this.productSerialNo = productSerialNo;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getPriceListing() {
        return priceListing;
    }

    public void setPriceListing(Double priceListing) {
        this.priceListing = priceListing;
    }

    public Double getPriceSelling() {
        return priceSelling;
    }

    public void setPriceSelling(Double priceSelling) {
        this.priceSelling = priceSelling;
    }

    public Double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "ProductEntity [id=" + id + ", productName=" + productName + ", productCode=" + productCode
                + ", productCategory=" + productCategory + ", productBrand=" + productBrand + ", productSerialNo="
                + productSerialNo + ", productBarcode=" + productBarcode + ", productDesc=" + productDesc
                + ", priceListing=" + priceListing + ", priceSelling=" + priceSelling + ", priceDiscount="
                + priceDiscount + ", productStatus=" + productStatus + "]";
    }


}
