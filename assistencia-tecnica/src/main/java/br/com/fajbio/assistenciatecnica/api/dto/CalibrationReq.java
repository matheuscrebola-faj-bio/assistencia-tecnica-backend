package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CalibrationReq(
        LocalDate data,
        String referenciaCertificado,
        LocalDate validade
) {
}
