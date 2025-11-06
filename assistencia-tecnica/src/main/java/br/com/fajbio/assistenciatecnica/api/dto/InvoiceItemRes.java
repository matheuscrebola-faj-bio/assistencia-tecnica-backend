package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class InvoiceItemRes {
    private Long id;
    private String descricao;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
}
