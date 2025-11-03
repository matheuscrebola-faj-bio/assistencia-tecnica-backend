package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkOrderRes {
    private Long id;
    private UserRes tecnico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String observacoes;
    private List<WorkLogRes> logs;
}
