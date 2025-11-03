package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.WorkLogRes;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.repository.WorkLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<WorkLogRes> encontrarPeloWorkOrderId(Long workOrderId) {
        return repository.findAllByWorkOrderId(workOrderId);
    }

    public WorkLogRes encontrarPeloWorkOrderId(Long workOrderId, Long logId) {
        List<WorkLogRes> res = encontrarPeloWorkOrderId(workOrderId);
        return res.stream()
                .filter(w -> w.getId().equals(logId))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void delecao(Long id) {
        repository.deleteById(id);
    }
}
