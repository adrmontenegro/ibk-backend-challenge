package com.adrdev.customer.products.mapper;

import com.adrdev.customer.products.model.entity.LoanEntity;
import com.adrdev.customers.products.model.LoanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "productType", source = "product.productType")
    LoanResponse mapEntityToResponse(LoanEntity entity);
}
