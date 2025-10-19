package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-order-status")
@RequiredArgsConstructor
public class SoStatusController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> listSoStatus(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-order-status/id"));
//        //TODO: lista catálogo de status possíveis.
//        return null;
//    }
}
