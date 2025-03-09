package com.api.nearby.account_service.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @Column
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Column
    @Email
    @NotBlank(message = "E-mail cannot be empty")
    private String email;

    @Column
    private byte[] avatar;

    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Profile type cannot be empty")
    private ProfileTypeEnum profileType;
}
