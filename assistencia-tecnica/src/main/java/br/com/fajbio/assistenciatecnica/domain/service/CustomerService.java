package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    @Transactional
    protected Customer salvar(Customer customer){
        return repository.save(customer);
    }

    public Customer encontrarPeloDocumento(String documento) {
        return repository.findByDocumento(documento);
    }

    public Customer adicionarOrdemServico(Customer customer) {
        return salvar(customer);
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

    public Customer atualizar(Customer customer) {
        return salvar(customer);
    }

    public Customer delecaoLogica(Customer customer) {
        return salvar(customer);
    }

    public void removerContato(Long customerId, Long contactId) {
        var customer = encontrarPeloId(customerId);
        // Remove da lista - o orphanRemoval = true faz a deleção automática
        customer.getContacts().removeIf(contact -> contact.getId().equals(contactId));
        repository.save(customer);
    }

    public void removerEndereco(Long customerId, Long addressId) {
        var customer = encontrarPeloId(customerId);
        // Remove da lista - o orphanRemoval = true faz a deleção automática
        customer.getAddresses().removeIf(contact -> contact.getId().equals(addressId));
        repository.save(customer);
    }
}
