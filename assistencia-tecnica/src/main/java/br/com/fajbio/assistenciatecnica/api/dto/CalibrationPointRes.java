package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class CalibrationPointRes {
    private Long id;
    private String grandeza;
    private BigDecimal valorNominal;
    private BigDecimal valorMedido;
    private BigDecimal incerteza;
}
