package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;

public record EquipmentTypeReq(
        EProductLine nome
) {
}
