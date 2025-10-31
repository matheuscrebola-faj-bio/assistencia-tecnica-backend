package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentModelReq;
import br.com.fajbio.assistenciatecnica.api.dto.EquipmentModelRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.EquipmentModelMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.EquipmentTypeMapper;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentType;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.EquipmentModelService;
import br.com.fajbio.assistenciatecnica.domain.service.EquipmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment-models")
@RequiredArgsConstructor
public class EquipmentModelsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final EquipmentModelService equipmentModelService;
    private final EquipmentModelMapper equipmentModelMapper;
    private final EquipmentTypeMapper equipmentTypeMapper;
    private final EquipmentTypeService equipmentTypeService;

    @GetMapping
    public ResponseEntity<List<EquipmentModelRes>> listEquipmentModels(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/equipment-models"));
        // lista modelos (com filtro por tipo).
        List<EquipmentModelRes> res = equipmentModelMapper.mappear(equipmentModelService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEquipmentModel(@RequestHeader Long id, @RequestBody EquipmentModelReq req){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/equipment-models"));
        // cria modelo.
        EquipmentType type = equipmentTypeService.cadastrar(equipmentTypeMapper.mappear(req.equipmentType()));
        equipmentModelService.cadastrar(equipmentModelMapper.mappear(req, type));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentModelRes> getEquipmentModel(@RequestHeader Long userId, @PathVariable Long equipmentId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/equipment-models/id"));
        // detalhe do modelo.
        EquipmentModelRes res = equipmentModelMapper.mappear(equipmentModelService.encontrarPeloId(equipmentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipmentModel(@RequestHeader Long userId, @PathVariable Long equipmentId, @RequestBody EquipmentModelReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/equipment-models/id"));
        // atualiza modelo.
        equipmentModelService.atualizar(equipmentId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipmentModel(@RequestHeader Long userId, @PathVariable Long equipmentId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/equipment-models/id"));
        // remove modelo.
        equipmentModelService.delecao(equipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
