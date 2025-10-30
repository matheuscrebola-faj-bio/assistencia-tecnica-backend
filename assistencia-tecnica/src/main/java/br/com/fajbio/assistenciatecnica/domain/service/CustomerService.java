package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.AddressReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerAddressReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerContactReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerUpdate;
import br.com.fajbio.assistenciatecnica.api.mapper.AddressMapper;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final AddressMapper addressMapper;

    @Transactional
    protected Customer salvar(Customer customer){
        return repository.save(customer);
    }

    public Customer encontrarPeloDocumento(String documento) {
        return repository.findByDocumento(documento);
    }

    public Customer cadastrar(Customer customer) {
        return salvar(customer);
    }

    public List<Customer> encontrarTodos() {
        return repository.findAll();
    }

    public Customer encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void removerContato(Long customerId, Long contactId) {
        var customer = encontrarPeloId(customerId);
        // Remove da lista - o orphanRemoval = true faz a deleção automática
        customer.getContacts().removeIf(contact -> contact.getId().equals(contactId));
    }

    @Transactional
    public void removerEndereco(Long customerId, Long addressId) {
        var customer = encontrarPeloId(customerId);
        // Remove da lista - o orphanRemoval = true faz a deleção automática
        customer.getAddresses().removeIf(address -> address.getId().equals(addressId));
    }

    @Transactional
    public void atualizar(Long customerId, CustomerUpdate update) {
        Customer customer = encontrarPeloId(customerId);
        customer.setNomeLegal(update.nomeLegal());
        customer.setDocumento(update.documento());
        customer.setEmail(customer.getEmail());
    }

    @Transactional
    public void delecaoLogica(Long customerId) {
        Customer customer = encontrarPeloId(customerId);
        customer.setAtivo(false);
    }

    @Transactional
    public void atualizar(Customer customer, CustomerContact customerContact) {
        customer.getContacts().add(customerContact);
    }

    @Transactional
    public void atualizar(Customer customer, CustomerAddress customerAddress) {
        customer.getAddresses().add(customerAddress);
    }

    @Transactional
    public void atualizar(Long customerId, Long contactId, CustomerContactReq req) {
        Customer customer = encontrarPeloId(customerId);

        CustomerContact contact = customer.getContacts().stream()
                .filter(c -> c.getId().equals(contactId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        // Atualizar os campos
        contact.setNome(req.nome());
        contact.setEmail(req.email());
        contact.setTelefone(req.telefone());
    }

    @Transactional
    public void atualizar(Long customerId, Long addressId, CustomerAddressReq req) {
        Customer customer = encontrarPeloId(customerId);

        CustomerAddress address = customer.getAddresses().stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        address.setTipo(req.tipo());
        addressMapper.atualizar(address.getAddress(), req.address());
    }

    @Transactional
    public void adicionarOrdemServico(Customer customer, ServiceOrder serviceOrder) {
        customer.getServiceOrders().add(serviceOrder);
    }
}
