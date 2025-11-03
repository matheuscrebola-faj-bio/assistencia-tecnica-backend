package br.com.fajbio.assistenciatecnica.api.dto;

public record CustomerReq(
        String nome,
        String documento,
        CustomerContactReq contact,
        CustomerAddressReq address
) {
}
