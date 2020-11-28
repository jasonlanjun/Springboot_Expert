package com.junlan.service;

import com.junlan.domain.CustomerEntity;
import com.junlan.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceInt {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }

    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    public CustomerEntity findOne(Long id) {
        return customerRepository.findOne(id);
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }

    public CustomerEntity findCustomerByMobile(String mobile) {
        return customerRepository.findCustomerByMobile(mobile);
    }
}
