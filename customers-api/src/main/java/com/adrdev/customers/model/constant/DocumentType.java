package com.adrdev.customers.model.constant;

import java.util.Arrays;

public enum DocumentType {
    DNI,
    PASS,
    CE,
    NONE;

    public static DocumentType fromString(String value) {
        return Arrays.stream(values())
                .filter(documentType -> documentType.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(DocumentType.NONE);
    }
}
