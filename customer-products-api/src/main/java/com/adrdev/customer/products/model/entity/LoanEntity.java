package com.adrdev.customer.products.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name="loan_number")
    private String loanNumber;
    @Column(name="principal_amount")
    private Float principalAmount;
    @Column(name="interest_rate")
    private Float interestRate;

    @Column(name="loan_term")
    private Integer loanTerm;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="monthly_payment")
    private Float monthlyPayment;
    @Column(name="outstanding_balance")
    private Float outstandingBalance;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private ProductEntity product;
}
