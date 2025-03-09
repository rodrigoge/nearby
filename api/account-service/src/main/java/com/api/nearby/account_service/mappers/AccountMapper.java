package com.api.nearby.account_service.mappers;

import com.api.nearby.account_service.db.Account;
import com.api.nearby.account_service.dtos.AccountRequest;
import com.api.nearby.account_service.dtos.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountMapper {

    @Autowired
    private static PasswordEncoder passwordEncoder;

    public static Account buildToAccount(AccountRequest accountRequest) {
        return Account
                .builder()
                .name(accountRequest.name())
                .email(accountRequest.email())
                .password(passwordEncoder.encode(accountRequest.password()))
                .avatar(accountRequest.avatar())
                .profileType(accountRequest.profileType())
                .build();
    }

    public static AccountResponse buildToAccountResponse(Account account) {
        return new AccountResponse(
                account.getName(),
                account.getEmail(),
                account.getAvatar(),
                account.getProfileType()
        );
    }
}
