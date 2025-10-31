package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.InitialTestReq;
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

    public InitialTest encontrarTestesIniciaisPeloServiceOrderId(Long serviceOrderId) {
        return repository.findByServiceOrderId(serviceOrderId);
    }

    @Transactional
    public void atualizarTeste(Long serviceOrderId, Long testId, InitialTestReq req) {
        InitialTest test = encontrarTestesIniciaisPeloServiceOrderId(serviceOrderId);
        if (test.getId().equals(testId)){
            test.setAparelho(req.aparelho());
            test.setValores(req.valores());
            test.setResultado(req.resultado());
        }
    }

    @Transactional
    public void exclusaoLogica(Long serviceOrderId, Long testId) {
        InitialTest test = encontrarTestesIniciaisPeloServiceOrderId(serviceOrderId);
        if (test.getId().equals(testId)){
            test.setAtivo(false);
        }
    }
}
