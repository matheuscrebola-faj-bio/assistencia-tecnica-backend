package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import lombok.Builder;

@Builder
public class SoStatusRes {
    private Integer id;
    private ESoStatus nome;
}
