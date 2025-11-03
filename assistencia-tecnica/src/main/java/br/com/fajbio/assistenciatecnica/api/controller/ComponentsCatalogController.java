package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.ComponentReq;
import br.com.fajbio.assistenciatecnica.api.dto.ComponentRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.ComponentMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.ComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
@RequiredArgsConstructor
public class ComponentsCatalogController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final ComponentMapper componentMapper;
    private final ComponentService componentService;

    @GetMapping
    public ResponseEntity<List<ComponentRes>> listComponents(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/components"));
        // lista componentes.
        List<ComponentRes> res = componentMapper.mappear(componentService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createComponent(@RequestHeader Long id, @RequestBody ComponentReq req){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/components"));
        // cria componente.
        componentService.cadastrar(componentMapper.mappear(req));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{componentId}")
    public ResponseEntity<ComponentRes> getComponent(@RequestHeader Long userId, @PathVariable Long componentId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/components/id"));
        // detalhe do componente.
        ComponentRes res = componentMapper.mappear(componentService.encontrarPeloId(componentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{componentId}")
    public ResponseEntity<?> updateComponent(@RequestHeader Long userId, @PathVariable Long componentId, @RequestBody ComponentReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/components/id"));
        // atualiza componente.
        componentService.atualizar(componentId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{componentId}")
    public ResponseEntity<?> deleteComponent(@RequestHeader Long userId, @PathVariable Long componentId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/components/id"));
        // remove componente.
        componentService.delecaoLogica(componentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
