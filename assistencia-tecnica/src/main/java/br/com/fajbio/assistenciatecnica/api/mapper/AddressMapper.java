package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.AddressReq;
import br.com.fajbio.assistenciatecnica.api.dto.AddressRes;
import br.com.fajbio.assistenciatecnica.domain.model.Address;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address mappear(AddressReq address) {
        return Address.builder()
                .rua(address.rua())
                .numero(address.numero())
                .complemento(address.complemento())
                .bairro(address.bairro())
                .cidade(address.cidade())
                .uf(address.uf())
                .cep(address.cep())
                .pais(address.pais())
                .build();
    }

    public AddressRes mappear(Address address) {
        return AddressRes.builder()
                .rua(address.getRua())
                .numero(address.getNumero())
                .complemento(address.getComplemento())
                .bairro(address.getBairro())
                .cidade(address.getCidade())
                .cep(address.getCep())
                .pais(address.getPais())
                .build();
    }

}
