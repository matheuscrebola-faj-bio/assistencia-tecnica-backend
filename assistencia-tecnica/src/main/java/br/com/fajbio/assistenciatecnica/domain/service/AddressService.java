package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Address;
import br.com.fajbio.assistenciatecnica.domain.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    @Transactional
    protected Address salvar(Address address){
        return repository.save(address);
    }
    public Address cadastrar(Address address) {
        return salvar(address);
    }
}
