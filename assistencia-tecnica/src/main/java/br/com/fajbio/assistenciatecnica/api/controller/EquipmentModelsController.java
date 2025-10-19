package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipment-models")
@RequiredArgsConstructor
public class EquipmentModelsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping
//    public ResponseEntity<?> listEquipmentModels(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/equipment-models"));
//        //TODO: lista modelos (com filtro por tipo).
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createEquipmentModel(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/equipment-models"));
//        //TODO: cria modelo.
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getEquipmentModel(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/equipment-models/id"));
//        //TODO: detalhe do modelo.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateEquipmentModel(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/equipment-models/id"));
//        //TODO: atualiza modelo.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteEquipmentModel(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/equipment-models/id"));
//        //TODO: remove modelo.
//        return null;
//    }
}
