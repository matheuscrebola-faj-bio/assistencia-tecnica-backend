package br.com.fajbio.assistenciatecnica.api.dto;

public record CustomerUpdate(
        String nomeLegal,
        String documento,
        String email
) {
}
