package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import br.com.fajbio.assistenciatecnica.domain.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository repository;

    public Equipment encontrarPeloCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }
}
