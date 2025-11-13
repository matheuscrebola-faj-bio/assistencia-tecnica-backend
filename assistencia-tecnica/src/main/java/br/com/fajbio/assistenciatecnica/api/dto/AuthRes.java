package br.com.fajbio.assistenciatecnica.api.dto;

public record AuthRes(
        String accessToken,
        String refreshToken,
        String tipo,
        UserRes usuario
) {
}
