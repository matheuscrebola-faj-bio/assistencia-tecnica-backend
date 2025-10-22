package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CustomerContactRes;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerRes;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerUpdate;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public CustomerRes mappear(Customer customer){
        return CustomerRes.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
                .email(customer.getEmail())
                .contacts(customer.getContacts())
                .addresses(customer.getAddresses())
                .build();
    }

    public List<CustomerRes> mappear(List<Customer> customers) {
        return customers.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public Customer mappear(Customer customer, CustomerUpdate update) {
        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(update.nomeLegal())
                .documento(update.documento())
                .email(update.email())
                .ativo(customer.getAtivo())
                .contacts(customer.getContacts())
                .addresses(customer.getAddresses())
                .serviceOrders(customer.getServiceOrders())
                .build();
    }

    public Customer mappear(Customer customer, boolean b) {
        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
                .email(customer.getEmail())
                .ativo(b)
                .contacts(customer.getContacts())
                .addresses(customer.getAddresses())
                .serviceOrders(customer.getServiceOrders())
                .build();
    }

    public Customer mappear(Customer customer, ServiceOrder service) {
        List<ServiceOrder> orders = customer.getServiceOrders();
        orders.addLast(service);
        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
                .email(customer.getEmail())
                .ativo(customer.getAtivo())
                .contacts(customer.getContacts())
                .addresses(customer.getAddresses())
                .serviceOrders(orders)
                .build();
    }

    public CustomerContactRes mappear(CustomerContact contact){
        return CustomerContactRes.builder()
                .id(contact.getId())
                .nome(contact.getNome())
                .email(contact.getEmail())
                .telefone(contact.getTelefone())
                .build();
    }

    public List<CustomerContactRes> mappearContato(List<CustomerContact> contacts) {
        return contacts.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public Customer mappear(Customer customer, CustomerContact contact) {
        List<CustomerContact> contacts = customer.getContacts();
        contacts.add(contact);
        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
                .email(customer.getEmail())
                .ativo(customer.getAtivo())
                .contacts(contacts)
                .addresses(customer.getAddresses())
                .serviceOrders(customer.getServiceOrders())
                .build();
    }
}
