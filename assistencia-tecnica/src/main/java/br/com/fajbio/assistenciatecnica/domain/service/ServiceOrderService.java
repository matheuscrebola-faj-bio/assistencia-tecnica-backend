package br.com.fajbio.assistenciatecnica.domain.service;

//import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrdersRes;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.repository.ServiceOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.findById(id).orElse(null);
    }

//    public List<ServiceOrdersRes> encontrarPeloStatusAtual(ESoStatus status) {
//        return repository.findAllByCurrentStatus(status);
//    }
}
