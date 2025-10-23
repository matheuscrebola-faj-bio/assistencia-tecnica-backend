package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

@Builder
public class AddressRes {
    private Long id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String pais;
}
