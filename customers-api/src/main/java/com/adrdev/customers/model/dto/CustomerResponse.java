package com.adrdev.customers.model.dto;

import com.adrdev.customers.model.constant.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String names;
    private String lastNames;
    private DocumentType documentType;
    private String documentNumber;
}
