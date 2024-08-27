package com.example.nobsv2.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="bank")
public class BankAccount {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;
}
