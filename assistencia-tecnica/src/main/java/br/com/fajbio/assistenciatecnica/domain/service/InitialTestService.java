package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.InitialTest;
import br.com.fajbio.assistenciatecnica.domain.repository.InitialTestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitialTestService {
    private final InitialTestRepository repository;

    @Transactional
    protected InitialTest salvar(InitialTest test){
        return repository.save(test);
    }

    public InitialTest cadastrar(InitialTest test) {
        return salvar(test);
    }
}
