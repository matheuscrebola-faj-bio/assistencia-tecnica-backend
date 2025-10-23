package br.com.fajbio.assistenciatecnica.api.dto;

import java.time.LocalDateTime;

public record WorkOrderReq(
        LocalDateTime dataFim,
        String observacoes,
        WorkLogReq worklog
) {
}
