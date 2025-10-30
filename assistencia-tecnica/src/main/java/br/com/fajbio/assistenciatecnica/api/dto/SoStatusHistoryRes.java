package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class SoStatusHistoryRes {
    private Long id;
    private SoStatusRes fromStatus;
    private SoStatusRes toStatus;
    private String changedBy;
    private LocalDateTime atualizadoEm;
}
