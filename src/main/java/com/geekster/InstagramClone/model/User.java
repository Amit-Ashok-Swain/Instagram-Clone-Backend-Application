package com.geekster.InstagramClone.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    @NotEmpty
    private String firstName;

    @Column(nullable = false)
    @NotEmpty
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String instagramName;

    private String instagramBio;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @Column(nullable = false)
    @Past // check by testing/passing future date
    @NotNull
    private LocalDate dOB;

    @Column(unique = true , nullable = false)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "\\d{2}-\\d{10}", message = "Phone number should be in the format XX-XXXXXXXXXX")
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
     private boolean isBlueTicked;// this should not be exposed to user : Hint : DTO


}
