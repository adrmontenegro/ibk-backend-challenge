package com.adrdev.customer.products.controller;

import com.adrdev.customer.products.service.LoanService;
import com.adrdev.customers.products.api.CustomerLoansApi;
import com.adrdev.customers.products.model.LoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController implements CustomerLoansApi {
    private final LoanService loanService;

    @Override
    public ResponseEntity<List<LoanResponse>> getLoans(String customerCode) {
        return ResponseEntity.ok(loanService.findLoansByCustomerCode(customerCode));
    }

}
