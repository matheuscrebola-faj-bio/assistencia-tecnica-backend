package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;

import java.util.List;

public record CustomerReq(
        String nomeLegal,
        String documento,
        String email,
        List<CustomerContact> contacts,
        List<CustomerAddress> addresses
) {
}
