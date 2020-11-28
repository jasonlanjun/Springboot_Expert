package com.junlan.repository;

import com.junlan.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT product_brand FROM PRODUCT_ENTITY WHERE product_category = ?1 ORDER BY product_brand", nativeQuery = true)
	List<String> findProductsByProductCategory(String productCategory);

    @Query(value = "SELECT product_name FROM PRODUCT_ENTITY WHERE product_brand = ?1 ORDER BY product_name", nativeQuery = true)
	List<String> findProductsByProductBrand(String productBrand);

    @Query(value = "SELECT * FROM PRODUCT_ENTITY WHERE product_code = ?1", nativeQuery = true)
	ProductEntity findProductByCode(String productCode);

    @Query(value = "SELECT * FROM PRODUCT_ENTITY WHERE product_barcode = ?1", nativeQuery = true)
	ProductEntity findProductByBarcode(String productBarcode);

    @Query(value = "SELECT * FROM PRODUCT_ENTITY WHERE product_serial_no = ?1", nativeQuery = true)
	ProductEntity findProductBySerialNo(String productSerial);

    @Query(value = "SELECT product_barcode FROM PRODUCT_ENTITY WHERE product_category = ?1 AND product_brand = ?2 AND product_name = ?3 ", nativeQuery = true)
	String findBarcodeOfProduct(String productCategory, String productBrand, String productName);
}
