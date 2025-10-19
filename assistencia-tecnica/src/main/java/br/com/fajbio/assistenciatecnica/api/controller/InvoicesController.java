package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoicesController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getInvoice(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/invoices/id"));
//        //TODO: detalhe da fatura.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateInvoice(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/invoices/id"));
//        //TODO: atualiza metadados da fatura.
//        return null;
//    }
//
//    @PostMapping("/{id}/issue")
//    public ResponseEntity<?> issueInvoice(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/issue"));
//        //TODO: atribui número, emite NF/notas, muda status e anexa PDF/XML.
//        return null;
//    }
//
//    @PostMapping("/{id}/cancel")
//    public ResponseEntity<?> cancelInvoice(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/cancel"));
//        //TODO: cancela a fatura (e registra motivo).
//        return null;
//    }
//
//    @PostMapping("/{id}/pay")
//    public ResponseEntity<?> payInvoice(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/pay"));
//        //TODO: registra pagamento (parcial/total) e atualiza status.
//        return null;
//    }
//
//    @GetMapping("/{id}/items")
//    public ResponseEntity<?> listInvoiceItems(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/invoices/id/items"));
//        //TODO: lista itens da fatura.
//        return null;
//    }
//
//    @PostMapping("/{id}/items")
//    public ResponseEntity<?> createInvoiceItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/items"));
//        //TODO: adiciona item à fatura.
//        return null;
//    }
//
//    @GetMapping("/{id}/items/{itemId}")
//    public ResponseEntity<?> getInvoiceItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/invoices/id/items/id"));
//        //TODO: detalhe do item.
//        return null;
//    }
//
//    @PutMapping("/{id}/items/{itemId}")
//    public ResponseEntity<?> updateInvoiceItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/invoices/id/items/id"));
//        //TODO: atualiza item.
//        return null;
//    }
//
//    @DeleteMapping("/{id}/items/{itemId}")
//    public ResponseEntity<?> deleteInvoiceItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/invoices/id/items/id"));
//        //TODO: remove item.
//        return null;
//    }
}
