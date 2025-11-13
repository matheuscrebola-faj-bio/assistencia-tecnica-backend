package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.AuthRes;
import br.com.fajbio.assistenciatecnica.api.dto.UserRes;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AuthMapper {

    public AuthRes mappear(UserRes userRes) {
        return AuthRes.builder()
                .token(UUID.randomUUID().toString())
                .refreshToken(String.valueOf(LocalDateTime.now().plusHours(6)))
                .user(userRes)
                .build();
    }
}
