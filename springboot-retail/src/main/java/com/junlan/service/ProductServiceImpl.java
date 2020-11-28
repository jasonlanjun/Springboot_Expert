package com.junlan.service;

import com.junlan.domain.ProductEntity;
import com.junlan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductServiceInt {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findOne(id);
    }

    public List<String> findProductsByProductCategory(String productCategory) {
        return productRepository.findProductsByProductCategory(productCategory);
    }

    public List<String> findProductsByProductBrand(String productBrand) {
        return productRepository.findProductsByProductBrand(productBrand);
    }

    public ProductEntity findProductByProductBarcode(String productBarcode) {
        return productRepository.findProductByBarcode(productBarcode);
    }

    public String findBarcodeOfProduct(String category, String brand, String product) {
        return productRepository.findBarcodeOfProduct(category, brand, product);
    }
}
