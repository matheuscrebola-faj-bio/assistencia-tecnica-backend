package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuthRes(
        String token,
        String refreshToken,
        UserRes user
) {
}
