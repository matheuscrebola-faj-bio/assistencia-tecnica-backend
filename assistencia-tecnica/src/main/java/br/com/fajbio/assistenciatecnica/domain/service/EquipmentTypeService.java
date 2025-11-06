package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentTypeReq;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentType;
import br.com.fajbio.assistenciatecnica.domain.repository.EquipmentTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentTypeService {
    private final EquipmentTypeRepository repository;

    @Transactional
    protected EquipmentType salvar(EquipmentType type){
        return repository.save(type);
    }

    public EquipmentType cadastrar(EquipmentType type) {
        return salvar(type);
    }

    public List<EquipmentType> encontrarTodos() {
        return repository.findAll();
    }

    public EquipmentType encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizar(Long id, EquipmentTypeReq req) {
        var equipment = encontrarPeloId(id);
        equipment.setNome(req.nome());
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
