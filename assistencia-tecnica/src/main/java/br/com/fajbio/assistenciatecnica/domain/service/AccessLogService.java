package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.AccessLog;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import br.com.fajbio.assistenciatecnica.domain.repository.AccessLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessLogService {
    private final AccessLogRepository repository;

    @Transactional
    protected AccessLog salvar(AccessLog log){
        return repository.save(log);
    }

    @Transactional
    protected void deletarVariosPeloId(List<Long> id){
        repository.deleteAllById(id);
    }

    public AccessLog registrar(AccessLog log) {
        return salvar(log);
    }

    public List<AccessLog> buscarRegistrosAnterioresA30Dias(LocalDateTime dataLimite){
        return repository.buscarRegistrosAnterioresA30Dias(dataLimite);
    }

    public void deletarRegistrosAnterioresA30Dias(List<AccessLog> logs){
        deletarVariosPeloId(
                logs.stream()
                        .map(AccessLog::getId)
                        .toList()
        );
    }
}
