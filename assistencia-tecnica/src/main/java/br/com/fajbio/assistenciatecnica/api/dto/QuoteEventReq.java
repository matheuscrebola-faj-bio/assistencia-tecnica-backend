package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EQuoteEvent;

public record QuoteEventReq(
        EQuoteEvent tipo
) {
}
