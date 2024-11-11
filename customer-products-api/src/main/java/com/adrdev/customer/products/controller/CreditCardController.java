package com.adrdev.customer.products.controller;

import com.adrdev.customer.products.service.CreditCardService;
import com.adrdev.customers.products.api.CustomerCreditCardsApi;
import com.adrdev.customers.products.model.CreditCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreditCardController implements CustomerCreditCardsApi {
    private final CreditCardService creditCardService;

    @Override
    public ResponseEntity<List<CreditCardResponse>> getCreditCards(String customerCode) {
        return ResponseEntity.ok(creditCardService.findCreditCardsByCustomerCode(customerCode));
    }
}
