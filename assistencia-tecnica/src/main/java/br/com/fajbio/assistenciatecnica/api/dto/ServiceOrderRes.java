package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import lombok.Builder;

@Builder
public class ServiceOrderRes {
    private Long id;
    private EquipmentRes equipment;
    private ESoStatus currentStatus;
    private EProductLine productLine;
}
