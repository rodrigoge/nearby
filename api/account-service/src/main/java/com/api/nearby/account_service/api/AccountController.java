package com.api.nearby.account_service.api;

import com.api.nearby.account_service.dtos.AccountRequest;
import com.api.nearby.account_service.dtos.AccountResponse;
import com.api.nearby.account_service.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuários", description = "Endpoints para gerenciar usuários")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Adiciona um novo usuário")
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest accountRequest) {
        var accountResponse = accountService.create(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }
}
