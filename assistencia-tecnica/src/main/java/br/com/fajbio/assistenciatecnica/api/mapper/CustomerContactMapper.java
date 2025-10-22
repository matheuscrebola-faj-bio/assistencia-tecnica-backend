package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CustomerContactReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerContactRes;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerContactMapper {
    public CustomerContactRes mappear(CustomerContact contact){
        return CustomerContactRes.builder()
                .id(contact.getId())
                .nome(contact.getNome())
                .email(contact.getEmail())
                .telefone(contact.getTelefone())
                .build();
    }

    public List<CustomerContactRes> mappear(List<CustomerContact> contacts) {
        return contacts.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public CustomerContact mappear(CustomerContactReq req){
        return CustomerContact.builder()

                .build();
    }
}
