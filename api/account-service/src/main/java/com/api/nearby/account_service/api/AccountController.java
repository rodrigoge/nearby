package com.api.nearby.account_service.api;

import com.api.nearby.account_service.dtos.CreateAccountRequest;
import com.api.nearby.account_service.dtos.CreateAccountResponse;
import com.api.nearby.account_service.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> create(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        var accountResponse = accountService.create(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }
}
