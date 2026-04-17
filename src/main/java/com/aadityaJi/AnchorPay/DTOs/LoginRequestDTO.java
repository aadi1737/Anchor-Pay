package com.aadityaJi.AnchorPay.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDTO {

    @Email
    @NotBlank(message = "Email can't be null")
    private String email;

    @NotBlank(message = "Password can't be null")
    private String password;
}
