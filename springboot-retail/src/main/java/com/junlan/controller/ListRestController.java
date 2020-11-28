package com.junlan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junlan.domain.*;
import com.junlan.service.*;
import com.junlan.util.GeneratePDFBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ListRestController {

    @Autowired
    private ProductServiceInt productService;

    @Autowired
    private PurchaseServiceInt purchaseService;

    @Autowired
    private SupplierServiceInt supplierService;

    @Autowired
    private StockServiceInt stockService;

    @Autowired
    private CityServiceInt cityService;

    @Autowired
    private BillServiceInt billService;

    @Autowired
    private SellingServiceInt sellingService;

    @Autowired
    private CustomerServiceInt customerService;

    @Autowired
    private UserServiceInt userService;

    private List<BillEntity> printingItems;

    private String customerName;

    @RequestMapping(path = "/admin/productlist", method = RequestMethod.GET)
    public List<ProductEntity> productList() {
        return productService.findAll();
    }

    @RequestMapping(path = "/admin/purchaselist", method = RequestMethod.GET)
    public List<PurchaseEntity> purchaseList() {
        return purchaseService.findAll();
    }

    @RequestMapping(path = "/admin/supplierlist", method = RequestMethod.GET)
    public List<SupplierEntity> supplierList() {
        return supplierService.findAll();
    }

    @RequestMapping(path = "/admin/stocklist", method = RequestMethod.GET)
    public List<StockEntity> stockList() {
        return stockService.findAll();
    }

    @RequestMapping(path = "/admin/customerlist", method = RequestMethod.GET)
    public List<CustomerEntity> customerList() {
        return customerService.findAll();
    }

    @RequestMapping(path = "/admin/billingitemslist", method = RequestMethod.GET)
    public List<BillEntity> billingItemsList() {
        return billService.findAll();
    }

    @RequestMapping(path = "/admin/saleslist", method = RequestMethod.GET)
    public List<SellingEntity> salesList() {
        return sellingService.findAll();
    }

    @RequestMapping(path = "/admin/userlist", method = RequestMethod.GET)
    public List<UserEntity> userList() {
        return userService.findAll();
    }

    @RequestMapping(value = "/admin/productBrandByCategory", method = RequestMethod.POST, produces = "application/json")
    public String productBrandByCategory(@RequestParam("productCategory") String productCategory)
            throws JsonProcessingException {
        List<String> productsCategories = productService.findProductsByProductCategory(productCategory);
        List<ProductEntity> productsBrands = productService.findAll();
        ArrayList<String> brandList = new ArrayList<>();
        ArrayList<String> productList = new ArrayList<>();
        for (String product : productsCategories) {
            if (!brandList.contains(product)) {
                brandList.add(product);
            }
        }
        for (ProductEntity product : productsBrands) {
            if (!productList.contains(product.getProductName())) {
                productList.add(product.getProductName());
            }
        }
        ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
        listOLists.add(brandList);
        listOLists.add(productList);
        ObjectMapper mapper = new ObjectMapper();
        String productCategoriesJSON = mapper.writeValueAsString(listOLists);
        return productCategoriesJSON;
    }

    @RequestMapping(value = "/admin/productByBrand", method = RequestMethod.POST, produces = "application/json")
    public String productByBrand(@RequestParam("productBrand") String productBrand) throws JsonProcessingException {
        List<String> products = productService.findProductsByProductBrand(productBrand);
        ArrayList<String> productList = new ArrayList<>();
        for (String product : products) {
            productList.add(product);
        }
        ObjectMapper mapper = new ObjectMapper();
        String productsJSON = mapper.writeValueAsString(productList);
        return productsJSON;
    }

    @RequestMapping(value = "/admin/cityByState", method = RequestMethod.POST, produces = "application/json")
    public String cityByState(@RequestParam("state") String state) throws JsonProcessingException {
        List<String> cities = cityService.findCityByState(state);
        ArrayList<String> cityList = new ArrayList<>();
        for (String city : cities) {
            cityList.add(city);
        }
        ObjectMapper mapper = new ObjectMapper();
        String cityJSON = mapper.writeValueAsString(cityList);
        return cityJSON;
    }

    @RequestMapping(value = "/admin/productByBarcode", method = RequestMethod.POST, produces = "application/json")
    public String productByBarcode(@RequestParam("productBarcode") String productBarcode)
            throws JsonProcessingException {
        ProductEntity product = productService.findProductByProductBarcode(productBarcode);
        ObjectMapper mapper = new ObjectMapper();
        String productJson = mapper.writeValueAsString(product);
        return productJson;
    }

    @RequestMapping(value = "/admin/customerByMobile", method = RequestMethod.POST, produces = "application/json")
    public String customerByMobile(@RequestParam("customerMobile") String customerMobile)
            throws JsonProcessingException {
        CustomerEntity customer = customerService.findCustomerByMobile(customerMobile);
        ObjectMapper mapper = new ObjectMapper();
        String customerJson = mapper.writeValueAsString(customer);
        return customerJson;
    }

    @RequestMapping(value = "/admin/saveBillingRecords", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String billingItems(@RequestBody BillingItemsEntity billingItems) throws JsonProcessingException {

        printingItems = new ArrayList<BillEntity>();
        customerName = customerService.findCustomerByMobile(billingItems.getBillingItems().get(0).getCustomerMobile())
                .getCustomerName();
        double totalAmount = 0.0;
        int totalQuantity = 0;

        for (BillEntity oneItem : billingItems.getBillingItems()) {
            billService.saveBill(oneItem);
            printingItems.add(oneItem);
            totalQuantity = totalQuantity + oneItem.getQuantity();
            totalAmount = totalAmount + oneItem.getNetPrice();

            StockEntity existStock = stockService.findStockByBarcode(oneItem.getProductBarcode());
            int stockQuantity = existStock.getStockQuantity();
            int purchasedQuantity = oneItem.getQuantity();
            // Should check first stock in stockEntity should be greater than
            // stock purchased

            int stockRemains = stockQuantity - purchasedQuantity;
            existStock.setStockQuantity(stockRemains);
            stockService.saveStock(existStock);
        }
        SellingEntity newSalesRecord = new SellingEntity();
        newSalesRecord.setBillDate(printingItems.get(0).getBillDate());
        newSalesRecord.setBillNumber(printingItems.get(0).getBillNumber());
        newSalesRecord.setCustomerMobile(printingItems.get(0).getCustomerMobile());
        newSalesRecord.setQuantity(totalQuantity);
        newSalesRecord.setTotalAmount(totalAmount);
        newSalesRecord.setCustomerName(customerName);
        sellingService.saveSalesRecord(newSalesRecord);
        String data = "{\"code\": 200}";
        return data;
    }

    @RequestMapping(value = "/admin/printBill", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> printBill() throws IOException {
        ByteArrayInputStream bis = GeneratePDFBill.billPrinting(printingItems, customerName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Bill.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
