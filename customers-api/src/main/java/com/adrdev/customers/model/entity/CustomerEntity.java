package com.adrdev.customers.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customers")
public class CustomerEntity {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uniqueCode;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_last_name")
    private String secondLastName;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "document_number")
    private String documentNumber;
}
