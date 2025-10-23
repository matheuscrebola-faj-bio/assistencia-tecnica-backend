package br.com.fajbio.assistenciatecnica.api.dto;

public record CustomerAddressReq(
        AddressReq address,
        String tipo
) {
}
