package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

@Builder
public class EquipmentModelRes {
    private Long id;
    private EquipmentTypeRes type;
    private String fabricante;
    private String modelo;
}
