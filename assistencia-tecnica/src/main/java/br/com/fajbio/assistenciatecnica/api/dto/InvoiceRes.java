package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EInvoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class InvoiceRes {
    private Long id;
    private String numero;
    private LocalDate dataEmissao;
    private BigDecimal valorTotal;
    private BigDecimal impostos;
    private EInvoice status;
}
