package com.wagdynavas.jam.service;

import com.wagdynavas.jam.auth.ApiKeyAuthentication;
import com.wagdynavas.jam.model.Client;
import com.wagdynavas.jam.repository.ClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final String X_API_KEY = "X-API-Key";
    private static final String X_CLIENT_ID = "X-Client-ID";

    private final BCryptPasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(X_API_KEY);
        String xClientID = request.getHeader(X_CLIENT_ID);

        Client client = clientRepository.getByXClientId(xClientID)
                .orElseGet(() -> {
                    String errorMessage = "Invalid X-Client-ID: ";
                    log.warn(errorMessage);
                    throw new BadCredentialsException(errorMessage);
                });

        if (!client.getDev1Key().equals(apiKey)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, NO_AUTHORITIES);
    }
}



