package com.junlan.service;

import com.junlan.domain.CustomerEntity;

import java.util.List;

public interface CustomerServiceInt {

    void saveCustomer(CustomerEntity contact);

    List<CustomerEntity> findAll();

    CustomerEntity findOne(Long id);

    void deleteCustomer(Long id);

    CustomerEntity findCustomerByMobile(String mobile);

}
