package br.com.fajbio.assistenciatecnica.api.dto;

import java.util.List;
import java.util.Set;

public record CustomerReq(
        String nomeLegal,
        String documento,
        String email,
        Set<CustomerContactReq> contacts,
        Set<CustomerAddressReq> addresses
) {
}
