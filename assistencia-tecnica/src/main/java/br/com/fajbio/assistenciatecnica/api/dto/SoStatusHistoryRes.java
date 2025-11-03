package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoStatusHistoryRes {
    private Long id;
    private SoStatusRes fromStatus;
    private SoStatusRes toStatus;
    private String changedBy;
    private LocalDateTime atualizadoEm;
}
