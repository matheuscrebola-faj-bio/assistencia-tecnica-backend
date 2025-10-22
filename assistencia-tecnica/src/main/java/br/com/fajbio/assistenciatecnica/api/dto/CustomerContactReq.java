package br.com.fajbio.assistenciatecnica.api.dto;

public record CustomerContactReq(
        String nome,
        String email,
        String telefone
) {
}
