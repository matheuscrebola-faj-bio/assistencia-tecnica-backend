package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.repository.WorkLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkLogService {
    private final WorkLogRepository repository;

    @Transactional
    protected WorkLog salvar(WorkLog workLog){
        return repository.save(workLog);
    }

    public WorkLog cadastrar(WorkLog workLog) {
        return salvar(workLog);
    }
}
