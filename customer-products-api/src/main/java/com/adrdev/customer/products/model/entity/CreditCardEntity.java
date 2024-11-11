package com.adrdev.customer.products.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "credit_cards")
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_card_id")
    private Long id;

    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "credit_limit")
    private Float creditLimit;
    @Column(name = "interest_rate")
    private Float interestRate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "billing_cycle")
    private Integer billingCycle;
    @Column(name = "current_balance")
    private Float currentBalance;
    @Column(name = "minimum_payment")
    private Float minimumPayment;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private ProductEntity product;
}
