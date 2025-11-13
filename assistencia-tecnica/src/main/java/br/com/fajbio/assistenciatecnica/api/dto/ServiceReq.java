package br.com.fajbio.assistenciatecnica.api.dto;

import java.math.BigDecimal;

public record ServiceReq(
        String nome,
        BigDecimal preco
) {
}
