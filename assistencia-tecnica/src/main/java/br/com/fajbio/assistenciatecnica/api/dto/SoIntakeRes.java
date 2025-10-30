package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class SoIntakeRes {
    private Long id;
    private LocalDate dataChegada;
    private Boolean lacreIntacto;
    private String observacoes;
}
