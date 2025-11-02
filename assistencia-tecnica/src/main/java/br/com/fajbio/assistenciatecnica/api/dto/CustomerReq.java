package br.com.fajbio.assistenciatecnica.api.dto;

public record CustomerReq(
        String nomeLegal,
        String documento,
        String email
) {
}
