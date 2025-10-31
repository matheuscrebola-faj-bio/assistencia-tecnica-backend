package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ComponentRes {
    private Long id;
    private String peca;
    private Short unidade;
    private BigDecimal preco;
}
