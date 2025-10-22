package br.com.fajbio.assistenciatecnica.api.dto;

import java.util.List;

public record QuoteReq(
        List<QuoteItemReq> quoteItemReq,
        String tipoQuoteEvent,
        String descricao
        ) {
}
