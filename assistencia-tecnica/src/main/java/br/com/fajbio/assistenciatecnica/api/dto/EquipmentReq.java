package br.com.fajbio.assistenciatecnica.api.dto;

import java.time.LocalDate;

public record EquipmentReq(
        Long customerId,
        String nome,
        String serial,
        LocalDate dataUltimaGarantia
) {
}
