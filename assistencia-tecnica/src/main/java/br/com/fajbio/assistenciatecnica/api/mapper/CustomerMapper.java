package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.*;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final AddressMapper addressMapper;
    private final CustomerContactMapper customerContactMapper;
    private final CustomerAddressMapper customerAddressMapper;

    public Customer mappear(CustomerReq req) {
        return Customer.builder()
                .nomeLegal(req.nomeLegal())
                .documento(req.documento())
//                .email(req.email())
                .ativo(true)
//                .contacts(customerContactMapper.mappear(req.contacts()))
//                .addresses(customerAddressMapper.mappear(req.addresses()))
                .build();
    }

    public CustomerRes mappear(Customer customer){
        return CustomerRes.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
//                .email(customer.getEmail())
//                .contacts(customer.getContacts())
//                .addresses(customer.getAddresses())
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
//                .email(update.email())
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
//                .email(customer.getEmail())
                .ativo(b)
                .contacts(customer.getContacts())
                .addresses(customer.getAddresses())
                .serviceOrders(customer.getServiceOrders())
                .build();
    }

    public Customer mappear(Customer customer, ServiceOrder service) {
        List<ServiceOrder> orders = customer.getServiceOrders();
        orders.add(service);
        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
//                .email(customer.getEmail())
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
//                .email(customer.getEmail())
                .ativo(customer.getAtivo())
                .contacts(contacts)
                .addresses(customer.getAddresses())
                .serviceOrders(customer.getServiceOrders())
                .build();
    }

    public Customer mappear(Customer customer, CustomerAddress address) {
        List<CustomerAddress> addresses = customer.getAddresses();
        addresses.add(address);
        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
//                .email(customer.getEmail())
                .ativo(customer.getAtivo())
                .contacts(customer.getContacts())
                .addresses(addresses)
                .serviceOrders(customer.getServiceOrders())
                .build();
    }

    public Customer mappear(Customer customer, Long contactId, CustomerContactReq req) {
        List<CustomerContact> contacts = customer.getContacts();

        // Encontra e atualiza o contato na lista
        contacts.stream()
                .filter(c -> c.getId().equals(contactId))
                .findFirst()
                .ifPresent(contact -> {
                    contact.setEmail(req.email());
                    contact.setTelefone(req.telefone());
                    contact.setNome(req.nome());
                });

        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
//                .email(customer.getEmail())
                .ativo(customer.getAtivo())
                .contacts(contacts)
                .addresses(customer.getAddresses())
                .serviceOrders(customer.getServiceOrders())
                .build();
    }

    public Customer mappear(Customer customer, Long addressId, CustomerAddressReq req) {
        List<CustomerAddress> addresses = customer.getAddresses();
        addresses.stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .ifPresent(address -> {
                    address.setAddress(addressMapper.mappear(req.address()));
                    address.setTipo(req.tipo());
                });

        return Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
//                .email(customer.getEmail())
                .ativo(customer.getAtivo())
                .contacts(customer.getContacts())
                .addresses(addresses)
                .serviceOrders(customer.getServiceOrders())
                .build();
    }


}
