package com.api.nearby.account_service.mappers;

import com.api.nearby.account_service.db.Account;
import com.api.nearby.account_service.dtos.AccountRequest;
import com.api.nearby.account_service.dtos.AccountResponse;
import com.api.nearby.account_service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class AccountMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account buildToAccount(AccountRequest accountRequest) {
        return Account
                .builder()
                .name(accountRequest.name())
                .email(accountRequest.email())
                .password(passwordEncoder.encode(accountRequest.password()))
                .avatar(accountRequest.avatar())
                .profileType(accountRequest.profileType())
                .build();
    }

    public AccountResponse buildToAccountResponse(Account account) {
        return new AccountResponse(
                account.getName(),
                account.getEmail(),
                account.getAvatar(),
                account.getProfileType(),
                Utils.formatDateToResponse(account.getCreatedAt()),
                Utils.formatDateToResponse(account.getUpdatedAt())
        );
    }
}
