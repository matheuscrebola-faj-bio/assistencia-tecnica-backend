package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.*;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.EquipmentComponentMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.EquipmentMapper;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentService equipmentService;
    private final EquipmentModelService equipmentModelService;
    private final CustomerService customerService;
    private final EquipmentComponentMapper equipmentComponentMapper;
    private final EquipmentComponentService equipmentComponentService;
    private final ComponentService componentService;

    @GetMapping
    public ResponseEntity<List<EquipmentRes>> listEquipments(
//            @RequestHeader Long id
        ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
        // lista equipamentos (cliente/modelo/serial).
        List<EquipmentRes> res = equipmentMapper.mappear(equipmentService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEquipment(
//            @RequestHeader Long id,
            @RequestBody EquipmentReq req
        ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/calibrations/id"));
        // cria equipamento vinculado ao cliente.
        EquipmentModel model = equipmentModelService.encontrarPeloNomeDoTipoDeEquipamento(req.nome());
        Customer customer = customerService.encontrarPeloId(req.customerId());
        equipmentService.cadastrar(equipmentMapper.mappear(req, model, customer));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<EquipmentRes> getEquipment(
            @RequestHeader Long id,
            @PathVariable Long equipmentId
        ){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
        // detalhe do equipamento.
        EquipmentRes res = equipmentMapper.mappear(equipmentService.encontrarPeloId(equipmentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipment(
//            @RequestHeader Long id,
            @PathVariable Long equipmentId,
            @RequestBody EquipmentUpdateReq req
        ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/calibrations/id"));
        //TODO: atualiza equipamento.
        equipmentService.atualizar(equipmentId, req);
        return null;
    }

    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<?> deleteEquipment(
            @RequestHeader Long id,
            @PathVariable Long equipmentId
        ){
        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/calibrations/id"));
        //TODO: remove/inativa equipamento.
        equipmentService.delecaoLogica(equipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{equipmentId}/components")
    public ResponseEntity<List<EquipmentComponentRes>> listEquipmentComponents(
//            @RequestHeader Long id
            @PathVariable Long equipmentId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
        //TODO: lista componentes instalados.
        List<EquipmentComponentRes> res = equipmentComponentMapper.mappear(equipmentService.encontrarPeloId(equipmentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{equipmentId}/components")
    public ResponseEntity<?> addEquipmentComponent(
//            @RequestHeader Long id
            @PathVariable Long equipmentId,
            @RequestBody EquipmentComponentReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/calibrations/id"));
        //TODO: adiciona/vincula componente ao equipamento.
        Component component = componentService.encontrarPelaPeca(req.peca());
        Equipment equipment = equipmentService.encontrarPeloId(equipmentId);
        EquipmentComponent equipmentComponent = equipmentComponentService.cadastrar(equipmentComponentMapper.mappear(equipment, component, req.quantidade()));
        equipmentService.atualizar(equipmentId, equipmentComponent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{equipmentId}/components/{equipmentComponentId}")
    public ResponseEntity<EquipmentComponentRes> getEquipmentComponent(
//            @RequestHeader Long id
            @PathVariable Long equipmentComponentId
        ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
        //TODO: detalhe do vínculo componente↔equipamento.
        EquipmentComponentRes res = equipmentComponentMapper.mappear(equipmentComponentService.encontrarPeloId(equipmentComponentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{equipmentId}/components/{equipmentComponentId}")
    public ResponseEntity<?> updateEquipmentComponent(
//            @RequestHeader Long id,
            @PathVariable Long equipmentComponentId,
            @RequestBody EquipmentComponentReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/calibrations/id"));
        //TODO: altera quantidade/dados do vínculo.
        equipmentComponentService.atualizar(equipmentComponentId, req.quantidade());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{equipmentId}/components/{equipmentComponentId}")
    public ResponseEntity<?> removeEquipmentComponent(
//            @RequestHeader Long id,
            @PathVariable Long equipmentComponentId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/calibrations/id"));
        //TODO: remove o vínculo.
        equipmentComponentService.deletar(equipmentComponentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
