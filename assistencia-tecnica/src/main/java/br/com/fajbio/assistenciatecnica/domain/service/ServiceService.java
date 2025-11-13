package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceReq;
import br.com.fajbio.assistenciatecnica.domain.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository repository;

    public List<br.com.fajbio.assistenciatecnica.domain.model.Service> encontrarTodosPeloNome(List<String> nomes) {
        return repository.findAllByNome(String.valueOf(nomes));
    }

    public List<br.com.fajbio.assistenciatecnica.domain.model.Service> encontrarTodos() {
        return repository.findAll();
    }

    @Transactional
    public void cadastrar(br.com.fajbio.assistenciatecnica.domain.model.Service service) {
        repository.save(service);
    }

    public br.com.fajbio.assistenciatecnica.domain.model.Service encontrarPeloId(Long serviceId) {
        return repository.findById(serviceId).orElse(null);
    }

    @Transactional
    public void atualizar(Long serviceId, ServiceReq req) {
        var service = encontrarPeloId(serviceId);
        service.setNome(req.nome());
        service.setPrecoBase(req.preco());
    }

    @Transactional
    public void deletar(Long serviceId) {
        repository.deleteById(serviceId);
    }

    public br.com.fajbio.assistenciatecnica.domain.model.Service encontrarPeloNome(String nome) {
        return repository.findByNome(nome);
    }
}
