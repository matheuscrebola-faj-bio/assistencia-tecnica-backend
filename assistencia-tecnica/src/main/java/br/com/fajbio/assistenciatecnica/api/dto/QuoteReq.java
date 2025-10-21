package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EQuoteStatus;

import java.util.List;

public record QuoteReq(
        EQuoteStatus quoteStatus,
        List<QuoteItemReq> quoteItemReq,
        String tipoQuoteEvent,
        String descricao
        ) {
}
