package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CustomerContactReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerContactRes;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
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

    public CustomerContact mappear(CustomerContactReq req, Customer customer){
        return CustomerContact.builder()
                .customerId(customer.getId())
                .customer(customer)
                .nome(req.nome())
                .email(req.email())
                .telefone(req.telefone())
                .build();
    }

    public CustomerContact mappear(Customer customer, Long contactId, CustomerContactReq req) {
        return CustomerContact.builder()
                .id(contactId)
                .customerId(customer.getId())
                .customer(customer)
                .nome(req.nome())
                .email(req.email())
                .telefone(req.telefone())
                .build();
    }

    public CustomerContact mappear(CustomerContactReq contact){
        return CustomerContact.builder()
                .nome(contact.nome())
                .email(contact.email())
                .telefone(contact.telefone())
                .build();
    }

    public List<CustomerContact> mappear(Set<CustomerContactReq> contacts) {
        return contacts.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }
}
