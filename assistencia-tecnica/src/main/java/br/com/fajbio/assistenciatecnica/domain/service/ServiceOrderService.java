package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.SoIntakeReq;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.repository.ServiceOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {
    private final ServiceOrderRepository repository;

    @Transactional
    protected ServiceOrder salvar(ServiceOrder serviceOrder) {
        return repository.save(serviceOrder);
    }

    public ServiceOrder cadastrar(ServiceOrder serviceOrder) {
        return salvar(serviceOrder);
    }

    public ServiceOrder encontrarPeloId(Long id) {
        return repository.findById(id).orElseThrow();
    }

//    @Transactional
//    public ServiceOrder registrarChegada(Long id, SoIntakeReq req) {
//        var serviceOrder = encontrarPeloId(id);
//
//    }

//    public List<ServiceOrdersRes> encontrarPeloStatusAtual(ESoStatus status) {
//        return repository.findAllByCurrentStatus(status);
//    }
}
