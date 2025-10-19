package br.com.fajbio.assistenciatecnica.api.dto;

import java.math.BigDecimal;

public record CalibrationPointReq (
        String grandeza,
        BigDecimal valorNominal,
        BigDecimal valorMedido,
        BigDecimal incerteza
){
}
