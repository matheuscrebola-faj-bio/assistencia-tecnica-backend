package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.EquipmentType;
import br.com.fajbio.assistenciatecnica.domain.repository.EquipmentTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
