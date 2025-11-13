package br.com.fajbio.assistenciatecnica.api.dto;

public record AuthReq(
        String email,
        String password
) {
}
