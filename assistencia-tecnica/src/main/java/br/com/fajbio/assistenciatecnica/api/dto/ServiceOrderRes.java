package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceOrderRes {
    private Long id;
    private EquipmentRes equipment;
    private ESoStatus currentStatus;
    private EProductLine productLine;
}
