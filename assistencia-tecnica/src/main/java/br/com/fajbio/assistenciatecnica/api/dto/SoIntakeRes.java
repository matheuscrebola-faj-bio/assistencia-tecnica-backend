package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoIntakeRes {
    private Long id;
    private LocalDate dataChegada;
    private Boolean lacreIntacto;
    private String observacoes;
}
