package com.aadityaJi.AnchorPay.DTOs;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO {

    @NotBlank(message = "Username can't be null")
    private String username;

    @NotBlank(message = "Password can't be Null")
    private String password;

    @NotBlank(message = "Password can't be Null")
    private String confirmPassword;

    @Email
    @NotBlank(message = "Email can't be null")
    private String email;

    @Min(18)
    private int age;

    @Size(min = 10,max = 10)
    private String phone;
}
