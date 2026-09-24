package com.wisdom.Ecom_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0, message = "wallet balance cannot drop below zero")
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    @Column(nullable = false)
    private Instant updatedAt;

    public Wallet() {}

    @PrePersist
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = Instant.now();
    }
}
