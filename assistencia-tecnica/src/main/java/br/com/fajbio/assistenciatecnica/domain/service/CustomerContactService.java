package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import br.com.fajbio.assistenciatecnica.domain.repository.CustomerContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerContactService {
    private final CustomerContactRepository repository;

    @Transactional
    protected CustomerContact salvar(CustomerContact contact){
        return repository.save(contact);
    }

    public CustomerContact cadastrar(CustomerContact contact) {
        return salvar(contact);
    }

    public CustomerContact encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CustomerContact atualizar(CustomerContact contact) {
     return salvar(contact);
    }
}
