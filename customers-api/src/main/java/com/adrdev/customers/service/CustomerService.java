package com.adrdev.customers.service;

import com.adrdev.customers.model.CustomerDto;

public interface CustomerService {
    CustomerDto findCustomerByUniqueCode(String uniqueCode);
}
