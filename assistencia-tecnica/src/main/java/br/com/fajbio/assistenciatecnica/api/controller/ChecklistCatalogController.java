package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checklist-catalogs")
@RequiredArgsConstructor
public class ChecklistCatalogController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping
//    public ResponseEntity<?> listChecklistCatalogs(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/checklist-catalogs"));
//        //TODO: lista modelos de checklist.
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createChecklistCatalog(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/checklist-catalogs"));
//        //TODO: cria modelo.
//        return null;
//    }
//
//    @GetMapping("/{catalogId}")
//    public ResponseEntity<?> getChecklistCatalog(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/checklist-catalogs/id"));
//        //TODO: detalhe do modelo.
//        return null;
//    }
//
//    @PutMapping("/{catalogId}")
//    public ResponseEntity<?> updateChecklistCatalog(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/checklist-catalogs/id"));
//        //TODO: atualiza modelo.
//        return null;
//    }
//
//    @DeleteMapping("/{catalogId}")
//    public ResponseEntity<?> deleteChecklistCatalog(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/checklist-catalogs/id"));
//        //TODO: remove modelo.
//        return null;
//    }
//
//    @GetMapping("/{catalogId}/items")
//    public ResponseEntity<?> listChecklistCatalogItems(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/checklist-catalogs/id/items"));
//        //TODO: lista itens do modelo.
//        return null;
//    }
//
//    @PostMapping("/{catalogId}/items")
//    public ResponseEntity<?> createChecklistCatalogItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/checklist-catalogs/id/items"));
//        //TODO: adiciona item ao modelo.
//        return null;
//    }
//
//    @GetMapping("/{catalogId}/items/{itemId}")
//    public ResponseEntity<?> getChecklistCatalogItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/checklist-catalogs/id/items/id"));
//        //TODO: detalhe do item do modelo.
//        return null;
//    }
//
//    @PutMapping("/{catalogId}/items/{itemId}")
//    public ResponseEntity<?> updateChecklistCatalogItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/checklist-catalogs/id/items/id"));
//        //TODO: atualiza item.
//        return null;
//    }
//
//    @PostMapping("/{catalogId}/items/{itemId}")
//    public ResponseEntity<?> deleteChecklistCatalogItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/checklist-catalogs/id/items/id"));
//        //TODO: remove item.
//        return null;
//    }
}
