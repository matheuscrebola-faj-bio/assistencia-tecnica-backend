package br.com.fajbio.assistenciatecnica.api.dto;

import java.time.LocalDate;

public record SoIntakeReq (LocalDate dataChegada, Boolean lacreIntacto, String observacoes){
}
