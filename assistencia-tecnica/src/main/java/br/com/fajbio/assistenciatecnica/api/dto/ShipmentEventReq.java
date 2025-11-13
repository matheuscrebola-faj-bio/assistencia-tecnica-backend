package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EShipmentEvent;

public record ShipmentEventReq(
        EShipmentEvent status,
        String localizacao
) {
}
