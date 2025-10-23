package br.com.fajbio.assistenciatecnica.api.dto;

public record AddressReq(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep,
        String pais
) {
}
