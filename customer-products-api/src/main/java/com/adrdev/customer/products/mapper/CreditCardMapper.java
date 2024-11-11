package com.adrdev.customer.products.mapper;

import com.adrdev.customer.products.model.entity.CreditCardEntity;
import com.adrdev.customers.products.model.CreditCardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "productType", source = "product.productType")
    CreditCardResponse mapEntityToResponse(CreditCardEntity entity);
}
