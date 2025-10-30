package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

@Builder
public class WorkLogRes {
    private Long id;
    private String evento;
}
