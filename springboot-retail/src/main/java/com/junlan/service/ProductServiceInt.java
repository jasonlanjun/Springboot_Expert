package com.junlan.service;

import com.junlan.domain.ProductEntity;

import java.util.List;

public interface ProductServiceInt {

    void saveProduct(ProductEntity user);

    List<ProductEntity> findAll();

    void deleteProduct(Long id);

    ProductEntity getProductById(Long id);

    List<String> findProductsByProductCategory(String productCategory);

    List<String> findProductsByProductBrand(String productBrand);

    ProductEntity findProductByProductBarcode(String barcode);

    String findBarcodeOfProduct(String category, String brand, String product);
}
