package com.adrdev.customer.products.service.impl;

import com.adrdev.customer.products.mapper.SavingsAccountMapper;
import com.adrdev.customer.products.persistence.SavingsAccountEntityRepository;
import com.adrdev.customer.products.service.SavingsAccountService;
import com.adrdev.customers.products.model.SavingsAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingsAccountServiceImpl implements SavingsAccountService {

    private final SavingsAccountEntityRepository savingsAccountRepository;
    private final SavingsAccountMapper savingsAccountMapper;

    @Override
    public List<SavingsAccountResponse> findSavingsAccountsByCustomerCode(String customerCode) {
       return savingsAccountRepository.findByCustomerId(customerCode)
               .stream()
               .map(savingsAccountMapper::mapEntityToResponse)
               .toList();
    }
}
