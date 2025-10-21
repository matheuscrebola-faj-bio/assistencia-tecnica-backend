package br.com.fajbio.assistenciatecnica.api.dto;

public record QuoteItemReq(
        String serviceName,
        String descricao,
        Byte quantidade
) {
}
