package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class EquipmentRes {
    private Long id;
    private LocalDate dataUltimaGarantia;
    private EquipmentModelRes model;
    private String serial;
}
