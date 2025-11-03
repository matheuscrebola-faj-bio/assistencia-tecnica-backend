package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalibrationPointRes {
    private Long id;
    private String grandeza;
    private BigDecimal valorNominal;
    private BigDecimal valorMedido;
    private BigDecimal incerteza;
}
