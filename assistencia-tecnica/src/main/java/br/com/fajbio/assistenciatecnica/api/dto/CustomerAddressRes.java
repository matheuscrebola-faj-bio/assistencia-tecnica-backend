package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;

@Builder
public class CustomerAddressRes {
    private Long id;
    private AddressRes address;
    private String tipo;
}
