package com.aadityaJi.AnchorPay.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Entity(name = "UserEntity")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @NotNull(message = "Username can not be null")
    private String username;

    @Column(nullable = false,unique = true)
    @Email
    @NotNull(message = "Email can not be null")
    private String email;

    @Column(nullable = false)
    private String password;

    @Min((18))
    private  int age;

    @Column(length = 10)
    @Size(min =10,max = 10)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAT;

    private boolean isActive;

    private int loginAttempts;

    @OneToOne
    @JoinColumn(name = "walletId")
    private WalletEntity wallet;


    public enum Role{
        USER,
        ADMIN
    };
}
