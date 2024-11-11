package com.adrdev.customers.service.impl;

import com.adrdev.customers.mapper.CustomerMapper;
import com.adrdev.customers.model.CustomerDto;
import com.adrdev.customers.model.constant.DocumentType;
import com.adrdev.customers.model.dto.CustomerResponse;
import com.adrdev.customers.persistence.CustomerRepository;
import com.adrdev.customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto findCustomerByUniqueCode(String uniqueCode) {
        return customerRepository.findByUniqueCode(uniqueCode)
                .map(customerMapper::mapEntityToDto)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
