package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EShipmentMode;

import java.time.LocalDate;

public record ShipmentReq(
        String transportadora,
        String codigoRastreio,
        LocalDate dataEnvio,
        EShipmentMode mode
) {
}
