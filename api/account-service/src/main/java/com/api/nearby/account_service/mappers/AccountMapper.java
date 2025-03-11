package com.api.nearby.account_service.mappers;

import com.api.nearby.account_service.db.Account;
import com.api.nearby.account_service.dtos.CreateAccountRequest;
import com.api.nearby.account_service.dtos.CreateAccountResponse;
import com.api.nearby.account_service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account buildToAccount(CreateAccountRequest createAccountRequest) {
        return Account
                .builder()
                .name(createAccountRequest.name())
                .email(createAccountRequest.email())
                .password(passwordEncoder.encode(createAccountRequest.password()))
                .profileType(createAccountRequest.profileType())
                .build();
    }

    public CreateAccountResponse buildToAccountResponse(Account account) {
        return new CreateAccountResponse(
                account.getName(),
                account.getEmail(),
                account.getProfileType(),
                Utils.formatDateToResponse(account.getCreatedAt()),
                Utils.formatDateToResponse(account.getUpdatedAt())
        );
    }
}
