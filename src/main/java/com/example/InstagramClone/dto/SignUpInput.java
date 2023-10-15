package com.example.InstagramClone.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {
    private Long userId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private Integer age;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    private String password;
    @Pattern(regexp = "\\d{2}-\\d{10}", message = "Phone number should be in the format XX-XXXXXXXXXX")
    private String phoneNumber;
}
