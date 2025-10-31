package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class InitialTestRes {
    private Long id;
    private UserRes tecnico;
    private String aparelho;
    private LocalDateTime criadoEm;
    private String valores;
    private String resultado;
}
