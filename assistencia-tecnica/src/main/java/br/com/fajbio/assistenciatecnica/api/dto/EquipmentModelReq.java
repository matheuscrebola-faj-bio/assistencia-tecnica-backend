package br.com.fajbio.assistenciatecnica.api.dto;

public record EquipmentModelReq(
        EquipmentTypeReq equipmentType,
        String fabricante,
        String modelo
) {
}
