package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.WorkLogRes;
import br.com.fajbio.assistenciatecnica.api.dto.WorkOrderReq;
import br.com.fajbio.assistenciatecnica.api.dto.WorkOrderRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.WorkLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.WorkOrderMapper;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.model.WorkOrder;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.WorkLogService;
import br.com.fajbio.assistenciatecnica.domain.service.WorkOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/work-orders")
@RequiredArgsConstructor
public class WorkOrdersController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final WorkOrderMapper workOrderMapper;
    private final WorkOrderService workOrderService;
    private final WorkLogMapper workLogMapper;
    private final WorkLogService workLogService;

    @GetMapping("/{workOrderId}")
    public ResponseEntity<WorkOrderRes> getWorkOrder(
            //@RequestHeader Long userId,
            @RequestParam Long workOrderId){
        //accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/work-orders/id"));
        // detalhe da OT.
        var workOrder = workOrderService.encontrarPeloId(workOrderId);
        if (workOrder == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        WorkOrderRes res = workOrderMapper.mappear(workOrder);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{workOrderId}")
    public ResponseEntity<?> updateWorkOrder(
//            @RequestHeader Long id,
            @PathVariable Long workOrderId,
            @RequestBody WorkOrderReq req){
        //accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/work-orders/id"));
        // atualiza OT (datas, observações).
        WorkOrder order = workOrderService.encontrarPeloId(workOrderId);
        WorkLog log = workLogService.cadastrar(workLogMapper.mappear(order, req.worklog()));
        workOrderService.atualizar(order.getId(), log, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{workOrderId}/close")
    public ResponseEntity<?> closeWorkOrder(
//            @RequestHeader Long id,
            @PathVariable Long workOrderId,
            @RequestBody WorkOrderReq req){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/work-orders/id"));
        // encerra OT, registra conclusão e pode acionar closeout.
        WorkOrder order = workOrderService.encontrarPeloId(workOrderId);
        WorkLog log = workLogService.cadastrar(workLogMapper.mappear(order, req.worklog()));
        workOrderService.atualizar(order.getId(), log, req, LocalDateTime.now());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{workOrderId}/logs")
    public ResponseEntity<List<WorkLogRes>> listWorkLogs(
            //@RequestHeader Long id
            @PathVariable Long workOrderId){
        //accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
        // lista logs/atividades da OT.
        List<WorkLogRes> res = workLogService.encontrarPeloWorkOrderId(workOrderId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
//
//    @PostMapping("/{workOrderId}/logs")
//    public ResponseEntity<?> createWorkLog(
//            @RequestHeader Long id,
//            @PathVariable Long workOrderId
//    ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/work-orders/id"));
//        //TODO: adiciona log (evento, duração).
//        return null;
//    }

    @GetMapping("/{workOrderId}/logs/{logId}")
    public ResponseEntity<WorkLogRes> getWorkLog(
//            @RequestHeader Long id,
            @RequestParam Long workOrderId,
            @RequestParam Long logId){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
        // detalhe do log.
        WorkLogRes res = workLogService.encontrarPeloWorkOrderId(workOrderId, logId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

//    @PutMapping("/{workOrderId}/logs/{logId}")
//    public ResponseEntity<?> updateWorkLog(
//            @RequestHeader Long id,
//            @RequestParam Long workOrderId,
//            @RequestParam Long logId){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
//        //TODO: atualiza log.
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @DeleteMapping("/{workOrderId}/logs/{logId}")
    public ResponseEntity<?> deleteWorkLog(
//            @RequestHeader Long id,
            @RequestParam Long workOrderId,
            @RequestParam Long logId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/work-orders/id"));
        //TODO: remove log.
        workLogService.delecao(logId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
