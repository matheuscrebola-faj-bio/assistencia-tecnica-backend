package br.com.fajbio.assistenciatecnica.domain.enums;

public enum ESoStatus {
    AGUARDANDO_RECEBIMENTO,
    TESTES_INICIAIS,
    //TODO: ambos são orçamento
    VALIDAR_ORACAMENTO, //já enviou o orçamento
    RETORNO_ORCAMENTO,
    LIBERADO_REPARO,
    EXPEDICAO,
    FATURAR,
    AGUARDANDO_RETIRADA
}
