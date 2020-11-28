package com.junlan.controller;

import com.junlan.domain.CityEntity;
import com.junlan.domain.CustomerEntity;
import com.junlan.domain.StateEntity;
import com.junlan.service.CityServiceInt;
import com.junlan.service.CustomerServiceInt;
import com.junlan.service.StateServiceInt;
import com.junlan.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerServiceInt customerService;

    @Autowired
    private StateServiceInt stateServiceInt;

    @Autowired
    private CityServiceInt cityServiceInt;

    @Autowired
    private JavaMailSender javaMailService;

    @Autowired
    private CommonUtils commonUtils;

    @GetMapping("/admin/newcustomer")
    public String newCustomer(Model model) {
        List<StateEntity> stateList = stateServiceInt.findAll();
        List<CityEntity> cityList = cityServiceInt.findAll();
        model.addAttribute("customer", new CustomerEntity());
        model.addAttribute("stateList", stateList);
        model.addAttribute("cityList", cityList);
        return "CustomerForm";
    }

    @PostMapping("/admin/savecustomer")
    public String saveCustomer(CustomerEntity customer) {
        customer.setCreatedDateTime(commonUtils.getTime());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(customer.getCustomerEmail());
        mailMessage.setSubject("Customer Registration");
        mailMessage.setText(
                "Hello " + customer.getCustomerName() + "\nYou have been registered as our customer successfully.");
        javaMailService.send(mailMessage);
        System.out.println("Email " + customer.getCustomerEmail());
        customerService.saveCustomer(customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/admin/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/customers";
    }

    @GetMapping("/admin/customer/update/{id}")
    public String updateCustomer(@PathVariable Long id, ModelMap model) {
        List<StateEntity> stateList = stateServiceInt.findAll();
        List<CityEntity> cityList = cityServiceInt.findAll();
        model.addAttribute("stateList", stateList);
        model.addAttribute("cityList", cityList);
        model.addAttribute("customer", customerService.findOne(id));
        return "CustomerForm";
    }
}
