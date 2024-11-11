package com.adrdev.customer.products.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "savings_accounts")
public class SavingsAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "savings_account_id")
    private Long id;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "account_number")
    private String accountNumber;
    private Float balance;
    @Column(name = "interest_rate")
    private Float interestRate;
    @Column(name = "min_balance")
    private Float minBalance;
    @Column(name = "withdrawal_limit")
    private Integer withdrawalLimit;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private ProductEntity product;
}