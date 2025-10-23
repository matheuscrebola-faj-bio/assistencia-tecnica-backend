package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.ChecklistRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.ChecklistMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.ChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checklists")
@RequiredArgsConstructor
public class ChecklistsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
//    private final ChecklistMapper checklistMapper;
//    private final ChecklistService checklistService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ChecklistRes> getChecklist(@RequestHeader Long userId, @PathVariable Long id){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/checklists/id"));
//        //TODO: detalhe do checklist (itens e respostas).
//
//        return null;
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateChecklist(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/checklists/id"));
//        //TODO: atualiza cabeçalho/itens.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteChecklist(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/checklists/id"));
//        //TODO: remove checklist.
//        return null;
//    }
}
