package com.adrdev.customer.products.service;

import com.adrdev.customers.products.model.SavingsAccountResponse;

import java.util.List;

public interface SavingsAccountService {

    List<SavingsAccountResponse> findSavingsAccountsByCustomerCode(String customerCode);
}
