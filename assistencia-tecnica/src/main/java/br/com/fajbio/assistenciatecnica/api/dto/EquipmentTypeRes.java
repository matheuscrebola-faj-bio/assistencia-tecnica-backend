package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentTypeRes {
    private Long id;
    private EProductLine nome;
}
