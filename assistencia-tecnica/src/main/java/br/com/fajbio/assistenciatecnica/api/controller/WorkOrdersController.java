package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work-orders")
@RequiredArgsConstructor
public class WorkOrdersController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping("/{workOrderId}")
//    public ResponseEntity<?> getWorkOrder(@RequestHeader Long userId, @RequestParam Long workOrderId){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/work-orders/id"));
//        //TODO: detalhe da OT.
//        return null;
//    }
//
//    @PutMapping("/{workOrderId}")
//    public ResponseEntity<?> updateWorkOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/work-orders/id"));
//        //TODO: atualiza OT (datas, observações).
//        return null;
//    }
//
//    @PostMapping("/{workOrderId}/close")
//    public ResponseEntity<?> closeWorkOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/work-orders/id"));
//        //TODO: encerra OT, registra conclusão e pode acionar closeout.
//        return null;
//    }
//
//    @GetMapping("/{workOrderId}/logs")
//    public ResponseEntity<?> listWorkLogs(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
//        //TODO: lista logs/atividades da OT.
//        return null;
//    }
//
//    @PostMapping("/{workOrderId}/logs")
//    public ResponseEntity<?> createWorkLog(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/work-orders/id"));
//        //TODO: adiciona log (evento, duração).
//        return null;
//    }
//
//    @GetMapping("/{workOrderId}/logs/{logId}")
//    public ResponseEntity<?> getWorkLog(@RequestHeader Long id, @RequestParam Long workOrderId, @RequestParam Long logId){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
//        //TODO: detalhe do log.
//        return null;
//    }
//
//    @GetMapping("/{workOrderId}/logs/{logId}")
//    public ResponseEntity<?> updateWorkLog(@RequestHeader Long id, @RequestParam Long workOrderId, @RequestParam Long logId){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
//        //TODO: atualiza log.
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/{workOrderId}/logs/{logId}")
//    public ResponseEntity<?> deleteWorkLog(@RequestHeader Long id, @RequestParam Long workOrderId, @RequestParam Long logId){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
//        //TODO: remove log.
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
