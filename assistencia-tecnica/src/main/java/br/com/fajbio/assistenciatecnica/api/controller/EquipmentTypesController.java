package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipment-types")
@RequiredArgsConstructor
public class EquipmentTypesController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping
//    public ResponseEntity<?> listEquipmentTypes(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/equipment-types"));
//        //TODO: lista tipos de equipamento.
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createEquipmentType(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/equipment-types"));
//        //TODO: cria tipo.
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getEquipmentType(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/equipment-types/id"));
//        //TODO: detalhe do tipo.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateEquipmentType(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/equipment-types/id"));
//        //TODO: atualiza tipo.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteEquipmentType(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/equipment-types/id"));
//        //TODO: remove tipo.
//        return null;
//    }
}
