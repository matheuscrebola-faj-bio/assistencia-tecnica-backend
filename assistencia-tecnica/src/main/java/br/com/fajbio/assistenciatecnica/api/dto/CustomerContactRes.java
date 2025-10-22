package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

@Builder
public class CustomerContactRes {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
