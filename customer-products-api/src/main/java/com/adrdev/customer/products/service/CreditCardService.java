package com.adrdev.customer.products.service;

//import com.adrdev.customers.products.model.CreditCardResponseInner;

import com.adrdev.customers.products.model.CreditCardResponse;

import java.util.List;

public interface CreditCardService {
    List<CreditCardResponse> findCreditCardsByCustomerCode(String customerCode);
}
