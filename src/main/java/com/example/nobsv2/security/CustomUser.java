package com.example.nobsv2.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="custom_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
}
