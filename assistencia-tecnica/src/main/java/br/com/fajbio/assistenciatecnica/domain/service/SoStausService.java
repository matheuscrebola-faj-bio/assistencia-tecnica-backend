package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.SoStatus;
import br.com.fajbio.assistenciatecnica.domain.repository.SoStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoStausService {
    private final SoStatusRepository repository;

    @Transactional
    protected SoStatus salvar(SoStatus status){
        return repository.save(status);
    }

    public SoStatus cadastrar(SoStatus status) {
        return salvar(status);
    }
}
