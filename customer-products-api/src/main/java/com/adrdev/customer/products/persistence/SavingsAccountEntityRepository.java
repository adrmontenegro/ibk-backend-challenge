package com.adrdev.customer.products.persistence;

import com.adrdev.customer.products.model.entity.SavingsAccountEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsAccountEntityRepository extends JpaRepository<SavingsAccountEntity, Long> {
    List<SavingsAccountEntity> findByCustomerId(String customerId);

}