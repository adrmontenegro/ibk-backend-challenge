package com.adrdev.customer.products.service.impl;

import com.adrdev.customer.products.mapper.LoanMapper;
import com.adrdev.customer.products.persistence.LoanRepository;
import com.adrdev.customer.products.service.LoanService;
import com.adrdev.customers.products.model.LoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanEntityRepository;
    private final LoanMapper loanMapper;

    @Override
    public List<LoanResponse> findLoansByCustomerCode(String customerCode) {
        return loanEntityRepository.findByCustomerId(customerCode)
                .stream()
                .map(loanMapper::mapEntityToResponse)
                .toList();
    }
}
