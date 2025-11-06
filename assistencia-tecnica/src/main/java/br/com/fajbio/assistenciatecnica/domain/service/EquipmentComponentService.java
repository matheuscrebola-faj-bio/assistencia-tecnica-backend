package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.EquipmentComponent;
import br.com.fajbio.assistenciatecnica.domain.repository.EquipmentComponentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentComponentService {
    private final EquipmentComponentRepository repository;

    public EquipmentComponent cadastrar(EquipmentComponent component) {
        return repository.save(component);
    }

    public EquipmentComponent encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizar(Long id, Byte qntd) {
        var equip = encontrarPeloId(id);
        equip.setQuantidade(qntd);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
