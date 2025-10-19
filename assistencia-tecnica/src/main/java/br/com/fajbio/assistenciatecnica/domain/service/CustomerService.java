package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public Customer encontrarPeloEmail(String email) {
        return repository.findByEmail(email);
    }
}
