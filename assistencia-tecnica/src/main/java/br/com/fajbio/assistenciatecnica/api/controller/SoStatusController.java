package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.SoStatusRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.SoStatusMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.SoStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service-order-status")
@RequiredArgsConstructor
public class SoStatusController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final SoStatusMapper soStatusMapper;
    private final SoStatusService soStatusService;

    @GetMapping("/{id}")
    public ResponseEntity<List<SoStatusRes>> listSoStatus(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-order-status/id"));
        //TODO: lista catálogo de status possíveis.
        List<SoStatusRes> res = soStatusMapper.mappear(soStatusService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
