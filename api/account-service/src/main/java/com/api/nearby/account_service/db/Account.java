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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @Column
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Minimum size must be 8")
    private String password;

    @Column
    @Email
    @NotBlank(message = "E-mail cannot be empty")
    private String email;

    @Column
    private byte[] avatar;

    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Profile type cannot be empty")
    private ProfileTypeEnum profileType;

    @Column(name = "created_at")
    @NotNull(message = "Created at cannot be null")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @NotNull(message = "Updated at cannot be null")
    private OffsetDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
