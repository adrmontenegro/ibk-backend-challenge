package com.adrdev.customer.products.service;


import com.adrdev.customers.products.model.LoanResponse;

import java.util.List;

public interface LoanService {
    List<LoanResponse> findLoansByCustomerCode(String customerCode);

}
