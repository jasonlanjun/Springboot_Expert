package com.junlan.service;

import com.junlan.domain.PurchaseEntity;

import java.util.List;

public interface PurchaseServiceInt {

    void savePurchase(PurchaseEntity user);

    List<PurchaseEntity> findAll();

    void deletePurchase(Long id);

    PurchaseEntity getPurchaseById(Long id);

    Long findMaxIdForProductEntity();
	
	/*public List<String> findProductsByProductCategory(String productCategory);

	public List<String> findProductsByProductBrand(String productBrand);*/

}
