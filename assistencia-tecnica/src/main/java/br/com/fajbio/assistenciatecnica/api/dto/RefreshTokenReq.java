package br.com.fajbio.assistenciatecnica.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenReq(
        @NotBlank(message = "Refresh token é obrigatório")
        String refreshToken
) {
}
