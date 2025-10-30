package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;

public record StatusReq(ESoStatus status) {
}
