package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;

import java.time.LocalDate;

public record ServiceOrderReq (
        String empresa,
        String cnpj,
        String contato,
        String setor,
        String email,
        String cep,
        String endereco,
        EProductLine produto,
        String serial,
        LocalDate calibracao,
        String descricao
        ){

}
