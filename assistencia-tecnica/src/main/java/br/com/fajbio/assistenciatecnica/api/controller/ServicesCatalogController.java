package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceReq;
import br.com.fajbio.assistenciatecnica.api.dto.ServiceRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.ServiceMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesCatalogController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final ServiceMapper serviceMapper;
    private final ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceRes>> listServices(
//            @RequestHeader Long id,
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/services"));
        // lista serviços do catálogo.
        List<ServiceRes> res = serviceMapper.mappear(serviceService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createService(
//            @RequestHeader Long id,
            @RequestBody ServiceReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/services"));
        // cria serviço.
        serviceService.cadastrar(serviceMapper.mappear(req));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<?> getService(
//            @RequestHeader Long id,
            @PathVariable Long serviceId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/services/id"));
        // detalhe do serviço.
        ServiceRes res = serviceMapper.mappear(serviceService.encontrarPeloId(serviceId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<?> updateService(
//            @RequestHeader Long id,
            @PathVariable Long serviceId,
            @RequestBody ServiceReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/services/id"));
        // atualiza serviço.
        serviceService.atualizar(serviceId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<?> deleteService(
//            @RequestHeader Long id,
            @PathVariable Long serviceId
    ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/services/id"));
        // remove serviço.
        serviceService.deletar(serviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
