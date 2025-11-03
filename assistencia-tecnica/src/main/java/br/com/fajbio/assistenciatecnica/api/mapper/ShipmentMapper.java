package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ShipmentReq;
import br.com.fajbio.assistenciatecnica.api.dto.ShipmentRes;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.Shipment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ShipmentMapper {
    public ShipmentRes mappear(Shipment shipment) {
        return ShipmentRes.builder()
                .id(shipment.getId())
                .transportadora(shipment.getTransportadora())
                .codigoRastreio(shipment.getCodigoRastreio())
                .dataEnvio(shipment.getDataEnvio())
                .mode(shipment.getMode())
                .build();
    }

    public Shipment mappear(ShipmentReq req, ServiceOrder serviceOrder) {
        return Shipment.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .transportadora(req.transportadora())
                .codigoRastreio(req.codigoRastreio())
                .dataEnvio(LocalDate.now())
                .mode(req.mode())
                .build();
    }
}
