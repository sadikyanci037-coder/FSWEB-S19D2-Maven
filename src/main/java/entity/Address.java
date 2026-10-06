package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;

    private Integer no;

    private String city;

    private String country;

    @Column(nullable = true)
    private String description;

    @OneToOne(mappedBy = "address")
    @JsonBackReference("customer-address")
    private Customer customer;
}