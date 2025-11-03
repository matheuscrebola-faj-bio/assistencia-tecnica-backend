package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.ShipmentReq;
import br.com.fajbio.assistenciatecnica.domain.model.Shipment;
import br.com.fajbio.assistenciatecnica.domain.model.ShipmentEvent;
import br.com.fajbio.assistenciatecnica.domain.repository.ShipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository repository;

    public Shipment encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void cadastrar(Shipment shipment) {
        repository.save(shipment);
    }

    @Transactional
    public void atualizar(Long shipmentId, ShipmentReq req) {
        var shipment = encontrarPeloId(shipmentId);
        shipment.setTransportadora(req.transportadora());
        shipment.setCodigoRastreio(req.codigoRastreio());
        shipment.setDataEnvio(req.dataEnvio());
        shipment.setMode(req.mode());
    }

    @Transactional
    public void atualizar(Long shipmentId, ShipmentEvent event) {
        var shipment = encontrarPeloId(shipmentId);
        shipment.getEvents().add(event);
    }
}
