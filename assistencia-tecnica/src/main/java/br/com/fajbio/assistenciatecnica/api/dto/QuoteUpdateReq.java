package br.com.fajbio.assistenciatecnica.api.dto;

public record QuoteUpdateReq(
        QuoteItemReq item,
        QuoteEventReq event
) {
}
