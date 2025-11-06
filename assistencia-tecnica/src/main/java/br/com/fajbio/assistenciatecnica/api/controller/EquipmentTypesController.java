package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentTypeReq;
import br.com.fajbio.assistenciatecnica.api.dto.EquipmentTypeRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.EquipmentTypeMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.EquipmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment-types")
@RequiredArgsConstructor
public class EquipmentTypesController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final EquipmentTypeMapper equipmentTypeMapper;
    private final EquipmentTypeService equipmentTypeService;

    @GetMapping
    public ResponseEntity<List<EquipmentTypeRes>> listEquipmentTypes(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/equipment-types"));
        //TODO: lista tipos de equipamento.
        List<EquipmentTypeRes> res = equipmentTypeMapper.mappear(equipmentTypeService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEquipmentType(
//            @RequestHeader Long id,
            @RequestBody EquipmentTypeReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/equipment-types"));
        //TODO: cria tipo.
        equipmentTypeService.cadastrar(equipmentTypeMapper.mappear(req));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{equipmentTypeId}")
    public ResponseEntity<EquipmentTypeRes> getEquipmentType(
//            @RequestHeader Long id,
            @PathVariable Long equipmentTypeId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/equipment-types/id"));
        //TODO: detalhe do tipo.
        EquipmentTypeRes res = equipmentTypeMapper.mappear(equipmentTypeService.encontrarPeloId(equipmentTypeId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{equipmentTypeId}")
    public ResponseEntity<?> updateEquipmentType(
//            @RequestHeader Long id,
            @PathVariable Long equipmentTypeId,
            @RequestBody EquipmentTypeReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/equipment-types/id"));
        //TODO: atualiza tipo.
        equipmentTypeService.atualizar(equipmentTypeId,req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{equipmentTypeId}")
    public ResponseEntity<?> deleteEquipmentType(
//            @RequestHeader Long id,
            @PathVariable Long equipmentTypeId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/equipment-types/id"));
        //TODO: remove tipo.
        equipmentTypeService.deletar(equipmentTypeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
