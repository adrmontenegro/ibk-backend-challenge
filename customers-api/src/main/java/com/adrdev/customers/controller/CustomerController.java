package com.adrdev.customers.controller;

import com.adrdev.customers.api.CustomersApi;
import com.adrdev.customers.model.CustomerDto;
import com.adrdev.customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomersApi {
    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDto> getCustomerByUniqueCode(String uniqueCode) {
        CustomerDto customer = customerService.findCustomerByUniqueCode(uniqueCode);
        return ResponseEntity.ok(customer);
    }
}
