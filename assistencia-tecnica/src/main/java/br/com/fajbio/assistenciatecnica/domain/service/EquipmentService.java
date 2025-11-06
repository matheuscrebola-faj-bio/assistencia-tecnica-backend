package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentUpdateReq;
import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentComponent;
import br.com.fajbio.assistenciatecnica.domain.repository.EquipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository repository;

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
        equipament.setSerial(req.serial());
        equipament.setDataUltimaGarantia(req.dataUltimaGarantia());
    }

    @Transactional
    public void delecaoLogica(Long id) {
        repository.deleteById(id);
    }

    public Equipment encontrarEquipamento(Long customerId, EProductLine produto, String serial, LocalDate dataUltimaGarantia) {
        return repository.findByCustomerId(customerId).stream()
                .filter(e -> e.getSerial() != null && e.getSerial().equalsIgnoreCase(serial))
                .filter(e -> e.getDataUltimaGarantia() != null && e.getDataUltimaGarantia().equals(dataUltimaGarantia))
                .filter(e -> e.getModel() != null
                        && e.getModel().getType() != null
                        && e.getModel().getType().getNome().equals(produto))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void atualizar(Long equipmentId, EquipmentComponent component) {
        var equipament = encontrarPeloId(equipmentId);
        equipament.getComponents().add(component);
    }
}
