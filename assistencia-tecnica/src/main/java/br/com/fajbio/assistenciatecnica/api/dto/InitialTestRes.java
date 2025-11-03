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
public class InitialTestRes {
    private Long id;
    private UserRes tecnico;
    private String aparelho;
    private LocalDateTime criadoEm;
    private String valores;
    private String resultado;
}
