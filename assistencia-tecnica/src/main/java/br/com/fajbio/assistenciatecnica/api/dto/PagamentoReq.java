package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EInvoice;

public record PagamentoReq(
        EInvoice invoice
) {
}
