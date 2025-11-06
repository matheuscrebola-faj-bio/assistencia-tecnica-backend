package br.com.fajbio.assistenciatecnica.api.dto;

import java.math.BigDecimal;

public record InvoiceItemReq(
        String descricao,
        BigDecimal quantidade,
        BigDecimal precoUnitario
) {
}
