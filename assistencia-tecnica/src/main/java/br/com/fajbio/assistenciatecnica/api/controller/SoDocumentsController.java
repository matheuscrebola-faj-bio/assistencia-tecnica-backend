package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.SoDocumentRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.SoDocumentMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.SoDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class SoDocumentsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final SoDocumentMapper soDocumentMapper;
    private final SoDocumentService soDocumentService;

    @GetMapping("/{documentId}")
    public ResponseEntity<SoDocumentRes> getDocument(
//            @RequestHeader Long id,
            @PathVariable Long documentId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/documents/id"));
        // detalhe/metadados e URL de download.
        SoDocumentRes res = soDocumentMapper.mappear(soDocumentService.encontrarPeloId(documentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<?> deleteDocument(
//            @RequestHeader Long id
            @PathVariable Long documentId
        ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/documents/id"));
        //TODO: exclui documento.
        soDocumentService.deletar(documentId);
        return null;
    }
}
