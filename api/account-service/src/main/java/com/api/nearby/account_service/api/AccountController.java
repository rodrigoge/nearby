package com.api.nearby.account_service.api;

import com.api.nearby.account_service.dtos.CreateAccountRequest;
import com.api.nearby.account_service.dtos.CreateAccountResponse;
import com.api.nearby.account_service.exceptions.CustomExceptionHandler;
import com.api.nearby.account_service.services.AccountService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @Tag(name = "Account Flows", description = "Account flows of Nearby API")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateAccountResponse.class)
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Error on create object",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionHandler.class)
                    )}),
    })
    public ResponseEntity<CreateAccountResponse> create(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        var accountResponse = accountService.create(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }
}
