package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import lombok.Builder;

import java.util.List;

@Builder
public class CustomerRes {
    private Long id;
    private String nomeLegal;
    private String documento;
    private String email;
}
