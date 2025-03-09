package com.api.nearby.account_service.dtos;

import com.api.nearby.account_service.db.ProfileTypeEnum;

public record AccountResponse(
        String name, String email, byte[] avatar, ProfileTypeEnum profileType) {
}
