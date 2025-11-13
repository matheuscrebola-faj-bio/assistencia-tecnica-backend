package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

@Builder
public record AuthRes(
        String token,
        String refreshToken,
        UserRes user
) {
}
