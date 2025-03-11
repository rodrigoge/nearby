package com.api.nearby.account_service.dtos;

import com.api.nearby.account_service.db.ProfileTypeEnum;

public record CreateAccountResponse(
        String name,
        String email,
        ProfileTypeEnum profileType,
        String createdAt,
        String updatedAt
) {
}
