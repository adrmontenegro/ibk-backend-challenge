package com.adrdev.customer.products.persistence;

import com.adrdev.customer.products.model.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<LoanEntity, Long> {
    List<LoanEntity> findByCustomerId(String customerId);
}