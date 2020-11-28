package com.junlan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "AdminDashboard";
    }

    @GetMapping("/admin/products")
    public String products() {
        return "ProductList";
    }

    @GetMapping("/admin/purchases")
    public String purchases() {
        return "PurchaseList";
    }

    @GetMapping("/admin/suppliers")
    public String suppliers() {
        return "SupplierList";
    }

    @GetMapping("/admin/stocks")
    public String stocks() {
        return "StockList";
    }

    @GetMapping("/admin/customers")
    public String customers() {
        return "CustomerList";
    }

    @GetMapping("/admin/billingitems")
    public String billingItems() {
        return "BillingItemsList";
    }

    @GetMapping("/admin/sales")
    public String sales() {
        return "SalesList";
    }

    @GetMapping("/admin/users")
    public String users() {
        return "UserList";
    }
}
