package com.api.nearby.account_service.configurations;

import com.api.nearby.account_service.db.AccountRepository;
import com.api.nearby.account_service.exceptions.CustomExceptionHandler;
import com.api.nearby.account_service.exceptions.ErrorTypeEnum;
import com.api.nearby.account_service.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.OffsetDateTime;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils JWTUtils;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer", "");
            var email = JWTUtils.verifySecretToken(token);
            var user = accountRepository.findByEmail(email).orElseThrow(() -> new CustomExceptionHandler(
                    HttpStatus.BAD_REQUEST,
                    OffsetDateTime.now(),
                    ErrorTypeEnum.INVALID_REQUEST,
                    "E-mail cannot be founded"
            ));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }
}
