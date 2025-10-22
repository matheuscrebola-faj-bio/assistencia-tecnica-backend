package br.com.fajbio.assistenciatecnica.api.dto;

public record UserUpdate(
        String username,
        String passwordHash,
        String email
) {
}
