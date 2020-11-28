package com.junlan.controller;

import com.junlan.domain.ProductEntity;
import com.junlan.domain.PurchaseEntity;
import com.junlan.domain.StockEntity;
import com.junlan.domain.SupplierEntity;
import com.junlan.service.ProductServiceInt;
import com.junlan.service.PurchaseServiceInt;
import com.junlan.service.StockServiceInt;
import com.junlan.service.SupplierServiceInt;
import com.junlan.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseServiceInt purchaseService;

    @Autowired
    private SupplierServiceInt supplierService;

    @Autowired
    private ProductServiceInt productService;

    @Autowired
    private StockServiceInt stockService;

    @Autowired
    private CommonUtils utils;

    @GetMapping("/admin/newpurchase")
    public String newPurchase(Model model) {
        List<SupplierEntity> supplierList = supplierService.findAll();
        List<ProductEntity> productList = productService.findAll();
        model.addAttribute("supplierlist", supplierList);
        model.addAttribute("productlist", productList);
        LocalDate currentDate = LocalDate.now();
        PurchaseEntity purchase = new PurchaseEntity();
        Long maxId = purchaseService.findMaxIdForProductEntity();
        purchase.setPoNumber("PO" + currentDate.getYear() + (maxId + 1));
        purchase.setPoStatus("Pending");
        model.addAttribute("purchase", purchase);
        return "PurchaseForm";
    }

    @PostMapping("/admin/savepurchase")
    public String savePurchase(PurchaseEntity purchase) {
        purchase.setPoDate(utils.getTime());
        if (purchase.getPoStatus().equals("Completed")) {
            String productBarcode = productService.findBarcodeOfProduct(purchase.getPoCategory(), purchase.getPoBrand(),
                    purchase.getPoProduct());
            boolean isStockNew = stockService.findStockByBarcode(productBarcode) == null;
            if (isStockNew) {
                StockEntity newStock = new StockEntity();
                newStock.setStockCategory(purchase.getPoCategory());
                newStock.setStockBrand(purchase.getPoBrand());
                newStock.setStockProduct(purchase.getPoProduct());
                newStock.setStockQuantity(purchase.getPoQuantity());
                newStock.setStockBarcode(productBarcode);
                newStock.setStockDate(utils.getTime());
                newStock.setStockStatus("Active");
                stockService.saveStock(newStock);
            } else {
                StockEntity existStock = stockService.findStockByBarcode(productBarcode);
                int existStockQuantity = existStock.getStockQuantity();
                int totalStockQuantity = Math.addExact(existStockQuantity, purchase.getPoQuantity());
                existStock.setStockCategory(purchase.getPoCategory());
                existStock.setStockBrand(purchase.getPoBrand());
                existStock.setStockProduct(purchase.getPoProduct());
                existStock.setStockQuantity(totalStockQuantity);
                existStock.setStockBarcode(productBarcode);
                existStock.setStockDate(utils.getTime());
                existStock.setStockStatus("Active");
                stockService.saveStock(existStock);
            }
        }
        purchaseService.savePurchase(purchase);
        return "redirect:/admin/purchases";
    }

    @GetMapping("/admin/purchase/delete/{id}")
    public String deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return "redirect:/admin/purchases";
    }

    @GetMapping("/admin/purchase/update/{id}")
    public String updatePurchase(@PathVariable Long id, ModelMap model) {
        List<SupplierEntity> supplierList = supplierService.findAll();
        List<ProductEntity> productList = productService.findAll();
        model.addAttribute("purchase", purchaseService.getPurchaseById(id));
        model.addAttribute("supplierlist", supplierList);
        model.addAttribute("productlist", productList);
        return "PurchaseForm";
    }

}
