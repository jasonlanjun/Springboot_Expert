package com.junlan.controller;

import com.junlan.domain.BillEntity;
import com.junlan.domain.CityEntity;
import com.junlan.domain.CustomerEntity;
import com.junlan.domain.StateEntity;
import com.junlan.service.BillServiceInt;
import com.junlan.service.CityServiceInt;
import com.junlan.service.StateServiceInt;
import com.junlan.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BillController {

    @Autowired
    private BillServiceInt billService;

    @Autowired
    private StateServiceInt stateServiceInt;

    @Autowired
    private CityServiceInt cityServiceInt;

    @Autowired
    private CommonUtils utils;

    @GetMapping("/admin/newbill")
    public String newBill(Model model) {
        BillEntity bill = new BillEntity();
        bill.setBillDate(utils.getTime());
        Long maxBillNumber = billService.getMaxBillNumber();
        bill.setBillNumber(maxBillNumber + 1);
        model.addAttribute("bill", bill);
        List<StateEntity> stateList = stateServiceInt.findAll();
        List<CityEntity> cityList = cityServiceInt.findAll();
        model.addAttribute("stateList", stateList);
        model.addAttribute("cityList", cityList);
        model.addAttribute("customer", new CustomerEntity());
        return "BillForm";
    }

    @PostMapping("/admin/savebill")
    public String saveBill(BillEntity bill) {
        return "redirect:/admin/newbill";
    }

}
