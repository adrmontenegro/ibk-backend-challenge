package com.adrdev.customer.products.persistence;

import com.adrdev.customer.products.model.entity.CreditCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardEntity, Long> {
    List<CreditCardEntity> findByCustomerId(String customerId);
}
