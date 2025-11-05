package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.QuoteRes;
import br.com.fajbio.assistenciatecnica.api.dto.QuoteUpdateReq;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.QuoteEventMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.QuoteItemMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.QuoteMapper;
import br.com.fajbio.assistenciatecnica.domain.model.Quote;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteEvent;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteItem;
import br.com.fajbio.assistenciatecnica.domain.model.Service;
import br.com.fajbio.assistenciatecnica.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuotesController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final QuoteMapper quoteMapper;
    private final QuoteService quoteService;
    private final QuoteItemMapper quoteItemMapper;
    private final QuoteItemService quoteItemService;
    private final QuoteEventMapper quoteEventMapper;
    private final QuoteEventService quoteEventService;
    private final ServiceService serviceService;

    @GetMapping("/{quoteId}")
    public ResponseEntity<QuoteRes> getQuote(
//            @RequestHeader Long id,
            @PathVariable Long quoteId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id"));
        // detalhe do orçamento (itens/eventos).
        QuoteRes res = quoteMapper.mappear(quoteService.encontrarPeloId(quoteId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{quoteId}")
    public ResponseEntity<?> updateQuote(
//            @RequestHeader Long id,
            @PathVariable Long quoteId,
            @RequestBody QuoteUpdateReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/quotes/id"));
        // atualiza orçamento (itens, validade).
        Quote quote = quoteService.encontrarPeloId(quoteId);
        Service service = serviceService.encontrarPeloNome(req.item().serviceName());
        QuoteItem item = quoteItemService.cadastrar(quoteItemMapper.mappear(req.item(), quote, service));
        QuoteEvent event = quoteEventService.cadastrar(quoteEventMapper.mappear(quote, req.event().tipo()));
        quoteService.atualizar(quoteId, item, event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{quoteId}")
    public ResponseEntity<?> deleteQuote(
//            @RequestHeader Long id,
            @PathVariable Long quoteId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/quotes/id"));
        // exclui orçamento.
        quoteService.deletar(quoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{quoteId}/send")
    public ResponseEntity<?> sendQuote(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/send"));
        //TODO: valida orçamento, gera PDF, envia por email ao cliente, muda OS para “aguardando_aprovacao” e registra evento.

        return null;
    }

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
