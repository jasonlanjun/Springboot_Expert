package com.junlan.controller;

import com.junlan.domain.ProductEntity;
import com.junlan.repository.ProductRepository;
import com.junlan.service.ProductServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductServiceInt productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin/newproduct")
    public String products(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "ProductForm";
    }

    @PostMapping("/admin/saveproduct")
    public String saveProduct(ProductEntity product) {
        Long receivedId = product.getId();
        if (receivedId.equals(new Long(0))) {
            if (productRepository.findProductByCode(product.getProductCode()) != null) {
                return "redirect:/admin/newproduct?codeExist";
            } else if (productRepository.findProductBySerialNo(product.getProductSerialNo()) != null) {
                return "redirect:/admin/newproduct?serialExist";
            } else if (productRepository.findProductByBarcode(product.getProductBarcode()) != null) {
                return "redirect:/admin/newproduct?barcodeExist";
            }
        } else {
            if (productRepository.findProductByCode(product.getProductCode()).getId() != receivedId) {
                return "redirect:/admin/newproduct?codeExist";
            } else if (productRepository.findProductBySerialNo(product.getProductSerialNo()).getId() != receivedId) {
                return "redirect:/admin/newproduct?serialExist";
            } else if (productRepository.findProductByBarcode(product.getProductBarcode()).getId() != receivedId) {
                return "redirect:/admin/newproduct?barcodeExist";
            }
        }
        productService.saveProduct(product);
        return "redirect:/admin/products";

    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "ProductForm";
    }
}
