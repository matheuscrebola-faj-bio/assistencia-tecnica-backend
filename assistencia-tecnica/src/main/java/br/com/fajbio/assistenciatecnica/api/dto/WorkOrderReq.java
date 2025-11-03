package br.com.fajbio.assistenciatecnica.api.dto;

public record WorkOrderReq(
        String observacoes,
        WorkLogReq worklog
) {
}
