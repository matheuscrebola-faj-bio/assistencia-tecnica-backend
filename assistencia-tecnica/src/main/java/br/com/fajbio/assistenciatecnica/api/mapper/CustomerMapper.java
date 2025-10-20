package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CustomerReq;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer mappear(CustomerReq req) {
        return Customer.builder()
                .nomeLegal(req.nomeLegal())
                .documento(req.documento())
                .email(req.email())
                .ativo(true)
                .contacts(req.contacts())
                .addresses(req.addresses())
                .build();
    }
}
