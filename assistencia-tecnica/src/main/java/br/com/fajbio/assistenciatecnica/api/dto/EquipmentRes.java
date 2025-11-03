package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentRes {
    private Long id;
    private LocalDate dataUltimaGarantia;
    private EquipmentModelRes model;
    private String serial;
}
