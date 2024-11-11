package com.adrdev.customer.products.mapper;

import com.adrdev.customer.products.model.entity.SavingsAccountEntity;
import com.adrdev.customers.products.model.SavingsAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SavingsAccountMapper {

    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "productType", source = "product.productType")
    SavingsAccountResponse mapEntityToResponse(SavingsAccountEntity entity);
//    SavingsAccountResponseInner mapEntityToResponse(SavingsAccountEntity entity);

}
