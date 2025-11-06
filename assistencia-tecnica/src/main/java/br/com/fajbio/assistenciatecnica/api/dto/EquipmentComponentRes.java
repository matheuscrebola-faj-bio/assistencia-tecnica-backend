package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class EquipmentComponentRes {
    private Long id;
    private Byte quantidade;
}
