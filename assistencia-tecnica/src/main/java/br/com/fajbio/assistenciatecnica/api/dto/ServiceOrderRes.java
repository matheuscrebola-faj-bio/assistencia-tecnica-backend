package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import lombok.Builder;

@Builder
public class ServiceOrderRes {
    private Long id;
    private Equipment equipment;
    private ESoStatus currentStatus;
    private EProductLine productLine;
}
