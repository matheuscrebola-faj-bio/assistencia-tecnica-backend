package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping
//    public ResponseEntity<?> listEquipments(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
//        //TODO: lista equipamentos (cliente/modelo/serial).
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createEquipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/calibrations/id"));
//        //TODO: cria equipamento vinculado ao cliente.
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getEquipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
//        //TODO: detalhe do equipamento.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateEquipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/calibrations/id"));
//        //TODO: atualiza equipamento.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteEquipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/calibrations/id"));
//        //TODO: remove/inativa equipamento.
//        return null;
//    }
//
//    @GetMapping("/{equipmentId}/components")
//    public ResponseEntity<?> listEquipmentComponents(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
//        //TODO: lista componentes instalados.
//        return null;
//    }
//
//    @PostMapping("/{equipmentId}/components")
//    public ResponseEntity<?> addEquipmentComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/calibrations/id"));
//        //TODO: adiciona/vincula componente ao equipamento.
//        return null;
//    }
//
//    @GetMapping("/{equipmentId}/components/{equipmentComponentId}")
//    public ResponseEntity<?> getEquipmentComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
//        //TODO: detalhe do vínculo componente↔equipamento.
//        return null;
//    }
//
//    @PutMapping("/{equipmentId}/components/{equipmentComponentId}")
//    public ResponseEntity<?> updateEquipmentComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/calibrations/id"));
//        //TODO: altera quantidade/dados do vínculo.
//        return null;
//    }
//
//    @DeleteMapping("/{equipmentId}/components/{equipmentComponentId}")
//    public ResponseEntity<?> removeEquipmentComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/calibrations/id"));
//        //TODO: remove o vínculo.
//        return null;
//    }
}
