package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.ComponentReq;
import br.com.fajbio.assistenciatecnica.domain.repository.ComponentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.fajbio.assistenciatecnica.domain.model.Component;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository repository;

    @Transactional
    protected Component salvar(Component component){
        return repository.save(component);
    }

    public List<Component> encontrarTodos() {
        return repository.findAll();
    }

    public void cadastrar(Component component) {
        salvar(component);
    }

    public Component encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizar(Long componentId, ComponentReq req) {
        Component component = encontrarPeloId(componentId);
        component.setPeca(req.peca());
        component.setUnidade(req.unidade());
        component.setPreco(req.preco());
    }

    @Transactional
    public void delecaoLogica(Long componentId) {
        repository.deleteById(componentId);
    }

    public Component encontrarPelaPeca(String peca) {
        return repository.findByPeca(peca);
    }
}
