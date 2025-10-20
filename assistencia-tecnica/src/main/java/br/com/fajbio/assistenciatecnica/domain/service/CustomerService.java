package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
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

    public Customer adicionarOrdemServico(Customer customer, ServiceOrder service) {
        List<ServiceOrder> orders = customer.getServiceOrders();
        orders.addLast(service);
        return salvar(Customer.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
                .email(customer.getEmail())
                .ativo(customer.getAtivo())
                .contacts(customer.getContacts())
                .addresses(customer.getAddresses())
                .serviceOrders(orders)
                .build());
    }

    public Customer cadastrar(Customer customer) {
        return salvar(customer);
    }
}
