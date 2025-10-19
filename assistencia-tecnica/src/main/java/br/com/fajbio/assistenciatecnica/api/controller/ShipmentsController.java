package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShipmentsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getShipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/shipments/id"));
//        //TODO: detalhe do envio.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateShipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/shipments/id"));
//        //TODO: atualiza envio (datas, transportadora).
//        return null;
//    }
//
//    @PostMapping("/{id}/track")
//    public ResponseEntity<?> trackShipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/shipments/id/track"));
//        //TODO: consulta integrador/transportadora, atualiza eventos e retorna situação.
//        return null;
//    }
//
//    @GetMapping("/{id}/events")
//    public ResponseEntity<?> listShipmentEvents(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/shipments/id/events"));
//        //TODO: lista eventos de rastreio.
//        return null;
//    }
//
//    @PostMapping("/{id}/events")
//    public ResponseEntity<?> createShipmentEvent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/shipments/id/events"));
//        //TODO: registra evento de rastreio manualmente (fallback).
//        return null;
//    }
}
