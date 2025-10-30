package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class WorkOrderRes {
    private Long id;
    private UserRes tecnico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String observacoes;
    private List<WorkLogRes> logs;
}
