package br.com.fajbio.assistenciatecnica.api.dto;

public record EquipmentComponentReq(
        String peca,
        Byte quantidade
) {
}
