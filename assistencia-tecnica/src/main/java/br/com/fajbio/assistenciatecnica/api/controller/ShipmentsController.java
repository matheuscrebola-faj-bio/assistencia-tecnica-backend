package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.ShipmentEventReq;
import br.com.fajbio.assistenciatecnica.api.dto.ShipmentEventRes;
import br.com.fajbio.assistenciatecnica.api.dto.ShipmentReq;
import br.com.fajbio.assistenciatecnica.api.dto.ShipmentRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.ShipmentEventMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.ShipmentMapper;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.Shipment;
import br.com.fajbio.assistenciatecnica.domain.model.ShipmentEvent;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.ServiceOrderService;
import br.com.fajbio.assistenciatecnica.domain.service.ShipmentEventService;
import br.com.fajbio.assistenciatecnica.domain.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShipmentsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentService shipmentService;
    private final ServiceOrderService serviceOrderService;
    private final ShipmentEventMapper shipmentEventMapper;
    private final ShipmentEventService shipmentEventService;

    @PostMapping
    public ResponseEntity<?> createShipment(
//            @RequestHeader Long id
            @PathVariable Long serviceOrderId,
            @RequestBody ShipmentReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/shipments/id"));
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        shipmentService.cadastrar(shipmentMapper.mappear(req, serviceOrder));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentRes> getShipment(
            //@RequestHeader Long id
            @PathVariable Long shipmentId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/shipments/id"));
        // detalhe do envio.
        ShipmentRes res = shipmentMapper.mappear(shipmentService.encontrarPeloId(shipmentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{shipmentId}")
    public ResponseEntity<?> updateShipment(
//            @RequestHeader Long id,
            @PathVariable Long shipmentId,
            @RequestBody ShipmentReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/shipments/id"));
        shipmentService.atualizar(shipmentId, req);
        // atualiza envio (datas, transportadora).
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/{id}/track")
//    public ResponseEntity<?> trackShipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/shipments/id/track"));
//        //TODO: consulta integrador/transportadora, atualiza eventos e retorna situação.
//        return null;
//    }
//
    @GetMapping("/{shipmentId}/events")
    public ResponseEntity<?> listShipmentEvents(
//            @RequestHeader Long id,
            @PathVariable Long shipmentId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/shipments/id/events"));
        // lista eventos de rastreio.
        List<ShipmentEventRes> res = shipmentEventMapper.mappear(shipmentEventService.encontrarPeloShipmentId(shipmentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}/events")
    public ResponseEntity<?> createShipmentEvent(
            @RequestHeader Long id,
            @PathVariable Long shipmentId,
            @RequestBody ShipmentEventReq req){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/shipments/id/events"));
        //TODO: registra evento de rastreio manualmente (fallback).
        Shipment shipment = shipmentService.encontrarPeloId(shipmentId);
        ShipmentEvent event = shipmentEventService.cadastrar(shipmentEventMapper.mappear(req, shipment));
        shipmentService.atualizar(shipmentId,event);
        return null;
    }
}
