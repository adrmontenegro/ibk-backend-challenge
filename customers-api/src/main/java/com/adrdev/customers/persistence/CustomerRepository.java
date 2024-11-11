package com.adrdev.customers.persistence;

import com.adrdev.customers.model.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByUniqueCode(String uniqueCode);
}
