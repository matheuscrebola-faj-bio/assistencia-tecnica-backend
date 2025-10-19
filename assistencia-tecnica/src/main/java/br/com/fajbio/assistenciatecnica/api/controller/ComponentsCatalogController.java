package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/components")
@RequiredArgsConstructor
public class ComponentsCatalogController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping
//    public ResponseEntity<?> listComponents(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/components"));
//        //TODO: lista componentes.
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/components"));
//        //TODO: cria componente.
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/components/id"));
//        //TODO: detalhe do componente.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/components/id"));
//        //TODO: atualiza componente.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteComponent(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/components/id"));
//        //TODO: remove componente.
//        return null;
//    }
}
