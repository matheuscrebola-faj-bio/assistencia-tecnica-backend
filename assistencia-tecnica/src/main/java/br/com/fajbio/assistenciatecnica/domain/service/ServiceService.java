package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.repository.ServiceRepository;
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
}
