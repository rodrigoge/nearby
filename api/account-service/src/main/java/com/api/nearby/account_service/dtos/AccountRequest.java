package com.api.nearby.account_service.dtos;

import com.api.nearby.account_service.db.ProfileTypeEnum;

public record AccountRequest(
        String name, String password, String email, byte[] avatar, ProfileTypeEnum profileType) {
}
