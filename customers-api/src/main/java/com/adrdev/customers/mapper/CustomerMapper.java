package com.adrdev.customers.mapper;

import com.adrdev.customers.model.CustomerDto;
import com.adrdev.customers.model.constant.DocumentType;
import com.adrdev.customers.model.dto.CustomerResponse;
import com.adrdev.customers.model.entity.CustomerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

//    @Mapping(target = "names", expression = "java(entity.getFirstName() + ' ' + entity.getMiddleName())")
//    @Mapping(target = "lastNames", expression = "java(entity.getLastName() + ' ' + entity.getSecondLastName())")
//    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeFromString")
//    CustomerResponse mapEntityToResponse(CustomerEntity entity);

    @Mapping(target = "names", expression = "java(entity.getFirstName() + ' ' + entity.getMiddleName())")
    @Mapping(target = "lastNames", expression = "java(entity.getLastName() + ' ' + entity.getSecondLastName())")
    @Mapping(target = "documentType", source = "documentType")
    CustomerDto mapEntityToDto(CustomerEntity entity);

//    @Named("documentTypeFromString")
//    default DocumentType documentTypeFromString(String value) {
//        return DocumentType.fromString(value);
//    }

    @BeforeMapping
    default void initializeEmptyFields(CustomerEntity entity) {
        if (entity.getMiddleName() == null) {
            entity.setMiddleName("");
        }
        if (entity.getSecondLastName() == null) {
            entity.setSecondLastName("");
        }
    }
}
