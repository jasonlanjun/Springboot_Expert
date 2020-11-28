package com.junlan.controller;

import com.junlan.domain.CityEntity;
import com.junlan.domain.StateEntity;
import com.junlan.domain.SupplierEntity;
import com.junlan.service.CityServiceInt;
import com.junlan.service.StateServiceInt;
import com.junlan.service.SupplierServiceInt;
import com.junlan.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    CommonUtils commonUtils;
    @Autowired
    private SupplierServiceInt supplierService;
    @Autowired
    private StateServiceInt stateServiceInt;
    @Autowired
    private CityServiceInt cityServiceInt;

    @GetMapping("/admin/newsupplier")
    public String newSupplier(Model model) {
        List<StateEntity> stateList = stateServiceInt.findAll();
        List<CityEntity> cityList = cityServiceInt.findAll();
        model.addAttribute("supplier", new SupplierEntity());
        model.addAttribute("stateList", stateList);
        model.addAttribute("cityList", cityList);
        return "SupplierForm";
    }

    @PostMapping("/admin/savesupplier")
    public String savesupplier(SupplierEntity supplier) {
        Long receivedId = supplier.getId();
        if (receivedId.equals(new Long(0))) {
            if (supplierService.findSupplierByName(supplier.getSupplierName()) != null) {
                return "redirect:/admin/newsupplier?supplierExist";
            } else if (supplierService.findSupplierByCode(supplier.getSupplierCode()) != null) {
                return "redirect:/admin/newsupplier?codeExist";
            } else if (supplierService.findSupplierByEmail(supplier.getSupplierEmail()) != null) {
                return "redirect:/admin/newsupplier?emailExist";
            } else if (!commonUtils.isContactValid(supplier.getSupplierPhone())) {
                return "redirect:/admin/newsupplier?invalidContact";
            }
        } else {
            if (supplierService.findSupplierByName(supplier.getSupplierName()).getId() != receivedId) {
                return "redirect:/admin/newsupplier?supplierExist";
            } else if (supplierService.findSupplierByCode(supplier.getSupplierCode()).getId() != receivedId) {
                return "redirect:/admin/newsupplier?codeExist";
            } else if (supplierService.findSupplierByEmail(supplier.getSupplierEmail()).getId() != receivedId) {
                return "redirect:/admin/newsupplier?emailExist";
            } else if (!commonUtils.isContactValid(supplier.getSupplierPhone())) {
                return "redirect:/admin/newsupplier?invalidContact";
            }
        }
        supplier.setSupplierStatus("Active");
        supplierService.saveSupplier(supplier);
        return "redirect:/admin/suppliers";
    }

    @GetMapping("/admin/supplier/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/admin/suppliers";
    }

    @GetMapping("/admin/supplier/update/{id}")
    public String updateSupplier(@PathVariable Long id, ModelMap model) {
        List<StateEntity> stateList = stateServiceInt.findAll();
        List<CityEntity> cityList = cityServiceInt.findAll();
        model.addAttribute("stateList", stateList);
        model.addAttribute("cityList", cityList);
        model.addAttribute("supplier", supplierService.findOne(id));
        return "SupplierForm";
    }

}
