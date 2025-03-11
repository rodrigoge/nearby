package com.api.nearby.account_service.services;

import com.api.nearby.account_service.db.AccountRepository;
import com.api.nearby.account_service.dtos.AccountRequest;
import com.api.nearby.account_service.dtos.AccountResponse;
import com.api.nearby.account_service.exceptions.CustomExceptionHandler;
import com.api.nearby.account_service.exceptions.ErrorTypeEnum;
import com.api.nearby.account_service.mappers.AccountMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@Log4j2
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    public AccountResponse create(AccountRequest accountRequest) {
        log.info("----- [Starting create account flow] -----");
        var accountEmail = accountRepository.findByEmail(accountRequest.email());
        if (accountEmail.isPresent()) {
            throw new CustomExceptionHandler(
                    HttpStatus.BAD_REQUEST,
                    OffsetDateTime.now(),
                    ErrorTypeEnum.INVALID_REQUEST,
                    "E-mail " + accountEmail.get().getEmail() + " already exists"
            );
        }
        var account = accountMapper.buildToAccount(accountRequest);
        account.setCreatedAt(OffsetDateTime.now());
        account.setUpdatedAt(OffsetDateTime.now());
        var accountSaved = accountRepository.save(account);
        var accountResponse = accountMapper.buildToAccountResponse(accountSaved);
        log.info("----- [Finishing create account flow] -----");
        return accountResponse;
    }
}
