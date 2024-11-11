package com.adrdev.customer.products.persistence;

import com.adrdev.customer.products.model.entity.SavingsAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsAccountRepository extends CrudRepository<SavingsAccountEntity, Long> {
    List<SavingsAccountEntity> findByCustomerId(String customerId);

}