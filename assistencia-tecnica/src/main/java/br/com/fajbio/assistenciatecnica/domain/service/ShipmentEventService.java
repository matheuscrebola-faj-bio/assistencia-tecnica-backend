package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.ShipmentEvent;
import br.com.fajbio.assistenciatecnica.domain.repository.ShipmentEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentEventService {
    private final ShipmentEventRepository repository;

    public List<ShipmentEvent> encontrarPeloShipmentId(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }

    @Transactional
    public ShipmentEvent cadastrar(ShipmentEvent event) {
        return repository.save(event);
    }
}
