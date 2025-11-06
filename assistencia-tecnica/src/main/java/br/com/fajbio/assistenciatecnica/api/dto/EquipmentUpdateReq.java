package br.com.fajbio.assistenciatecnica.api.dto;

import java.time.LocalDate;

public record EquipmentUpdateReq(
        String serial,
        LocalDate dataUltimaGarantia
) {
}
