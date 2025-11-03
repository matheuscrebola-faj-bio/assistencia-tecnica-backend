package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import br.com.fajbio.assistenciatecnica.domain.repository.CustomerAddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {
    private final CustomerAddressRepository repository;

    @Transactional
    public List<CustomerAddress> cadastrar(List<CustomerAddress> addresses) {
        return repository.saveAll(addresses);
    }

    @Transactional
    public CustomerAddress cadastrar(CustomerAddress address) {
        return repository.save(address);
    }
}
