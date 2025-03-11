package com.api.nearby.account_service.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JWTUtils {

    @Value("${secret.key}")
    private String secretKey;

    public String generateSecretToken(String email) {
        var instantNow = Instant.now();
        var expiresAt = instantNow.plus(10 * 60, ChronoUnit.SECONDS);
        return JWT
                .create()
                .withClaim("email", email)
                .withSubject(email)
                .withIssuedAt(instantNow)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String verifySecretToken(String secretToken) {
        return JWT
                .require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(secretToken)
                .getSubject();
    }
}
