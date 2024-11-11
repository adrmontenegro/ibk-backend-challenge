package com.adrdev.customer.products.service.impl;

import com.adrdev.customer.products.mapper.CreditCardMapper;
import com.adrdev.customer.products.persistence.CreditCardRepository;
import com.adrdev.customer.products.service.CreditCardService;
//import com.adrdev.customers.products.model.CreditCardResponseInner;
import com.adrdev.customers.products.model.CreditCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;

    @Override
    public List<CreditCardResponse> findCreditCardsByCustomerCode(String customerCode) {
        return creditCardRepository.findByCustomerId(customerCode)
                .stream()
                .map(creditCardMapper::mapEntityToResponse)
                .toList();
    }
}
