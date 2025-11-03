package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ShipmentEventReq;
import br.com.fajbio.assistenciatecnica.api.dto.ShipmentEventRes;
import br.com.fajbio.assistenciatecnica.domain.model.Shipment;
import br.com.fajbio.assistenciatecnica.domain.model.ShipmentEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShipmentEventMapper {
    public List<ShipmentEventRes> mappear(List<ShipmentEvent> shipmentEvents) {
        return shipmentEvents.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public ShipmentEventRes mappear(ShipmentEvent shipmentEvent) {
        return ShipmentEventRes.builder()
                .id(shipmentEvent.getShipmentId())
                .dataHora(shipmentEvent.getDataHora())
                .status(shipmentEvent.getStatus())
                .localizacao(shipmentEvent.getLocalizacao())
                .build();
    }

    public ShipmentEvent mappear(ShipmentEventReq req, Shipment shipment) {
        return ShipmentEvent.builder()
                .shipmentId(shipment.getId())
                .shipment(shipment)
                .dataHora(LocalDateTime.now())
                .status(req.status())
                .localizacao(req.localizacao())
                .build();
    }
}
