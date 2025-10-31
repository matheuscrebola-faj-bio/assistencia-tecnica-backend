package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentReq;
import br.com.fajbio.assistenciatecnica.api.dto.EquipmentRes;
import br.com.fajbio.assistenciatecnica.api.dto.EquipmentUpdateReq;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.EquipmentMapper;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentModel;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.CustomerService;
import br.com.fajbio.assistenciatecnica.domain.service.EquipmentModelService;
import br.com.fajbio.assistenciatecnica.domain.service.EquipmentService;
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

    @GetMapping
    public ResponseEntity<List<EquipmentRes>> listEquipments(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
        // lista equipamentos (cliente/modelo/serial).
        List<EquipmentRes> res = equipmentMapper.mappear(equipmentService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEquipment(@RequestHeader Long id, @RequestBody EquipmentReq req){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/calibrations/id"));
        // cria equipamento vinculado ao cliente.
        EquipmentModel model = equipmentModelService.encontrarPeloNomeDoTipoDeEquipamento(req.nome());
        Customer customer = customerService.encontrarPeloId(req.customerId());
        equipmentService.cadastrar(equipmentMapper.mappear(req, model, customer));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentRes> getEquipment(@RequestHeader Long id, @PathVariable Long equipmentId){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/calibrations/id"));
        // detalhe do equipamento.
        EquipmentRes res = equipmentMapper.mappear(equipmentService.encontrarPeloId(equipmentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateEquipment(@RequestHeader Long id, @PathVariable Long equipmentId, @RequestBody EquipmentUpdateReq req){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/calibrations/id"));
//        //TODO: atualiza equipamento.
//        return null;
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipment(@RequestHeader Long id, @PathVariable Long equipmentId){
        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/calibrations/id"));
        //TODO: remove/inativa equipamento.
        equipmentService.delecaoLogica(equipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
