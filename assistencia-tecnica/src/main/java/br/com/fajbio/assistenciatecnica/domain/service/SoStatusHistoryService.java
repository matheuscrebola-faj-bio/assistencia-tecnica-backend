package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.SoStatusHistory;
import br.com.fajbio.assistenciatecnica.domain.repository.SoStatusHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoStatusHistoryService {
    private final SoStatusHistoryRepository repository;

    @Transactional
    protected SoStatusHistory salvar(SoStatusHistory so){
        return repository.save(so);
    }

    public SoStatusHistory cadastrar(SoStatusHistory so) {
        return salvar(so);
    }
}
