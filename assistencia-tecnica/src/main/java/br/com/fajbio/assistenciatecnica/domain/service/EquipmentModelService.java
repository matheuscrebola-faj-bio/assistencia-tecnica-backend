package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentModelReq;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentModel;
import br.com.fajbio.assistenciatecnica.domain.repository.EquipmentModelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentModelService {
    private final EquipmentModelRepository repository;

    public List<EquipmentModel> encontrarTodos() {
        return repository.findAll();
    }

    @Transactional
    protected EquipmentModel salvar(EquipmentModel model) {
        return repository.save(model);
    }

    public EquipmentModel cadastrar(EquipmentModel model) {
        return salvar(model);
    }

    public EquipmentModel encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizar(Long id, EquipmentModelReq req) {
        var equipment = encontrarPeloId(id);
        equipment.setFabricante(req.fabricante());
        equipment.setModelo(req.modelo());
    }

    @Transactional
    public void delecao(Long id) {
        repository.deleteById(id);
    }

    public EquipmentModel encontrarPeloNomeDoTipoDeEquipamento(String nome) {
        return repository.findByEquipmentTypeNome(nome);
    }
}
