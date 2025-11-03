package br.com.fajbio.assistenciatecnica.api.dto;

public record ShipmentEventReq(
        String status,
        String localizacao
) {
}
