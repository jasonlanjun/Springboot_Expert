/*package com.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.retail.domain.BillEntity;
import com.retail.domain.CustomerEntity;
import com.retail.service.BillServiceInt;
import com.retail.util.CommonUtils;

@Controller
public class SellingController {

	@Autowired
	private BillServiceInt billService;

	@Autowired
	private CommonUtils utils;

	@GetMapping("/demo")
	public String demo(Model model) {
		BillEntity bill = new BillEntity();
		bill.setBillDate(utils.getTime());
		Long maxBillNumber = billService.getMaxBillNumber();
		bill.setBillNumber(maxBillNumber + 1);
		model.addAttribute("bill", bill);
		return "Demo";
	}

	@GetMapping("/newbill")
	public String billingCustomer(Model model) {
		BillEntity bill = new BillEntity();
		bill.setBillDate(utils.getTime());
		Long maxBillNumber = billService.getMaxBillNumber();
		bill.setBillNumber(maxBillNumber + 1);
		model.addAttribute("bill", bill);
		model.addAttribute("customer", new CustomerEntity());
		return "Bill";
	}
	
	@PostMapping("/savebill")
	public String saveBill(BillEntity bill, BindingResult result) {
		return "redirect:/newbill";
	}
}
*/