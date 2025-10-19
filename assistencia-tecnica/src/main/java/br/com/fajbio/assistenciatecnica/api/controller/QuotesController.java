package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuotesController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping("/{quoteId}")
//    public ResponseEntity<?> getQuote(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id"));
//        //TODO: detalhe do orçamento (itens/eventos).
//        return null;
//    }
//
//    @PutMapping("/{quoteId}")
//    public ResponseEntity<?> updateQuote(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/quotes/id"));
//        //TODO: atualiza orçamento (itens, validade).
//        return null;
//    }
//
//    @DeleteMapping("/{quoteId}")
//    public ResponseEntity<?> deleteQuote(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/quotes/id"));
//        //TODO: exclui orçamento.
//        return null;
//    }
//
//    @PostMapping("/{quoteId}/send")
//    public ResponseEntity<?> sendQuote(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/send"));
//        //TODO: valida orçamento, gera PDF, envia por email ao cliente, muda OS para “aguardando_aprovacao” e registra evento.
//        return null;
//    }
//
//    @PostMapping("/{quoteId}/approve")
//    public ResponseEntity<?> approveQuote(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/approve"));
//        //TODO: marca como aprovado (por cliente), cria/abre ordem de trabalho e atualiza status da OS.
//        return null;
//    }
//
//    @PostMapping("/{quoteId}/reject")
//    public ResponseEntity<?> rejectQuote(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/reject"));
//        //TODO: marca como rejeitado e atualiza status.
//        return null;
//    }
//
//    @GetMapping("/{quoteId}/events")
//    public ResponseEntity<?> listQuoteEvents(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id/events"));
//        //TODO: lista eventos do orçamento (enviado, visualizado, aprovado…).
//        return null;
//    }
//
//    @GetMapping("/{quoteId}/items")
//    public ResponseEntity<?> listQuoteItems(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id/items"));
//        return null;
//    }
//
//    @PostMapping("/{quoteId}/items")
//    public ResponseEntity<?> createQuoteItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/items"));
//        return null;
//    }
//
//    @GetMapping("/{quoteId}/items/{itemId}")
//    public ResponseEntity<?> getQuoteItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id/items/id"));
//        return null;
//    }
//
//    @PutMapping("/{quoteId}/items/{itemId}")
//    public ResponseEntity<?> updateQuoteItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/quotes/id/items/id"));
//        return null;
//    }
//
//    @DeleteMapping("/{quoteId}/items/{itemId}")
//    public ResponseEntity<?> deleteQuoteItem(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/quotes/id/items/id"));
//        return null;
//    }
}
