package br.com.fajbio.assistenciatecnica.api.dto;

import java.time.LocalDate;

public record EquipmentUpdateReq(
        CustomerReq customer,
        EquipmentModelReq model,
        String serial,
        LocalDate dataUltimaGarantia
) {
}
