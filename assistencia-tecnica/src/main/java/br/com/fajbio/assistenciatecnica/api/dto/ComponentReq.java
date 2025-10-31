package br.com.fajbio.assistenciatecnica.api.dto;

import java.math.BigDecimal;

public record ComponentReq(
        String peca,
        Short unidade,
        BigDecimal preco
) {
}
