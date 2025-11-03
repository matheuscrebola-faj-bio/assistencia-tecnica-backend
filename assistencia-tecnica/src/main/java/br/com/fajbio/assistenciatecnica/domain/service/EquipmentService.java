package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentUpdateReq;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import br.com.fajbio.assistenciatecnica.domain.repository.EquipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository repository;

    public Equipment encontrarPeloCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }

    public List<Equipment> encontrarTodos() {
        return repository.findAll();
    }

    @Transactional
    protected Equipment salvar(Equipment equipment){
        return repository.save(equipment);
    }

    public Equipment cadastrar(Equipment equipment) {
        return salvar(equipment);
    }

    public Equipment encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizar(Long equipmentId, EquipmentUpdateReq req) {
        var equipament = encontrarPeloId(equipmentId);
        //equipament.set;
    }

    @Transactional
    public void delecaoLogica(Long id) {
        repository.deleteById(id);
    }

    public Equipment encontrarPeloSerial(String serial) {
        return repository.findBySerial(serial);
    }
}
