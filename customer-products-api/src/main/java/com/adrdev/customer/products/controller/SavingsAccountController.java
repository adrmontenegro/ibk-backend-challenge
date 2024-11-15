package com.adrdev.customer.products.controller;

import com.adrdev.customer.products.service.SavingsAccountService;
import com.adrdev.customers.products.api.CustomerSavingsAccountsApi;
import com.adrdev.customers.products.model.SavingsAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SavingsAccountController implements CustomerSavingsAccountsApi {

    private final SavingsAccountService savingsAccountService;

    public ResponseEntity<List<SavingsAccountResponse>> getSavingsAccounts( String customerCode) {
        return ResponseEntity.ok(savingsAccountService.findSavingsAccountsByCustomerCode(customerCode));
    }
}
