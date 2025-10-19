package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesCatalogController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping
//    public ResponseEntity<?> listServices(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/services"));
//        //TODO: lista serviços do catálogo.
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createService(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/services"));
//        //TODO: cria serviço.
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getService(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/services/id"));
//        //TODO: detalhe do serviço.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateService(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/services/id"));
//        //TODO: atualiza serviço.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteService(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/services/id"));
//        //TODO: remove serviço.
//        return null;
//    }

}
