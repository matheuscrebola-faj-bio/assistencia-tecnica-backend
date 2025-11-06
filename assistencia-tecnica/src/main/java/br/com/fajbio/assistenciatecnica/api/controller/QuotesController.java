package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.*;
import br.com.fajbio.assistenciatecnica.api.mapper.*;
import br.com.fajbio.assistenciatecnica.domain.enums.EQuoteEvent;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final ServiceMapper serviceMapper;
    private final ServiceOrderService serviceOrderService;
    private final ServiceOrderMapper serviceOrderMapper;
    private final UserService userService;
    private final SoStatusHistoryMapper soStatusHistoryMapper;
    private final SoStatusHistoryService soStatusHistoryService;
    private final SoStatusService soStatusService;
    private final SoStatusMapper soStatusMapper;

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

//    @PostMapping("/{quoteId}/send")
//    public ResponseEntity<?> sendQuote(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/send"));
//        //TODO: valida orçamento, gera PDF, envia por email ao cliente, muda OS para “aguardando_aprovacao” e registra evento.
//
//        return null;
//    }

    @PostMapping("/{quoteId}/approve")
    public ResponseEntity<?> approveQuote(
            @RequestHeader Long userId,
            @PathVariable Long quoteId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/approve"));
        // marca como aprovado (por cliente), cria/abre ordem de trabalho e atualiza status da OS.
        User user = userService.encontrarPeloId(userId);
        Quote quote = quoteService.encontrarPeloId(quoteId);
        QuoteEvent quoteEvent = quoteEventMapper.mappear(quote, EQuoteEvent.APROVACAO, user);
        quoteService.atualizar(quoteId,quoteEvent);
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(quote.getServiceOrderId());
        SoStatus soStatus = soStatusService.cadastrar(soStatusMapper.mappear(ESoStatus.LIBERADO_REPARO));
        SoStatusHistory statusHistory = soStatusHistoryService.cadastrar(soStatusHistoryMapper.mappear(serviceOrder,soStatus,user));
        serviceOrderService.atualizar(quote.getServiceOrderId(), statusHistory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{quoteId}/reject")
    public ResponseEntity<?> rejectQuote(
            @RequestHeader Long userId,
            @PathVariable Long quoteId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/reject"));
        // marca como rejeitado e atualiza status.
        User user = userService.encontrarPeloId(userId);
        Quote quote = quoteService.encontrarPeloId(quoteId);
        QuoteEvent quoteEvent = quoteEventMapper.mappear(quote, EQuoteEvent.REVISAO, user);
        quoteService.atualizar(quoteId,quoteEvent);
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(quote.getServiceOrderId());
        SoStatus soStatus = soStatusService.cadastrar(soStatusMapper.mappear(ESoStatus.RETORNO_ORCAMENTO));
        SoStatusHistory statusHistory = soStatusHistoryService.cadastrar(soStatusHistoryMapper.mappear(serviceOrder,soStatus,user));
        serviceOrderService.atualizar(quote.getServiceOrderId(), statusHistory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{quoteId}/events")
    public ResponseEntity<List<QuoteEventRes>> listQuoteEvents(
//            @RequestHeader Long id,
            @PathVariable Long quoteId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id/events"));
        //TODO: lista eventos do orçamento (enviado, visualizado, aprovado…).
        List<QuoteEventRes> res = quoteEventMapper.mappear(quoteService.encontrarPeloId(quoteId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{quoteId}/items")
    public ResponseEntity<List<QuoteItemRes>> listQuoteItems(
//            @RequestHeader Long id
            @PathVariable Long quoteId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id/items"));
        List<QuoteItemRes> res = quoteItemMapper.mappear(quoteService.encontrarPeloId(quoteId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{quoteId}/items")
    public ResponseEntity<?> createQuoteItem(
//            @RequestHeader Long id,
            @PathVariable Long quoteId,
            @RequestBody QuoteItemReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/quotes/id/items"));
        Quote quote = quoteService.encontrarPeloId(quoteId);
        Service service = serviceService.encontrarPeloNome(req.serviceName());
        QuoteItem quoteItem = quoteItemService.cadastrar(quoteItemMapper.mappear(req,quote,service));
        quoteService.atualizar(quoteId,quoteItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{quoteId}/items/{itemId}")
    public ResponseEntity<QuoteItemRes> getQuoteItem(
//            @RequestHeader Long id,
            @PathVariable Long itemId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/quotes/id/items/id"));
        QuoteItemRes res = quoteItemMapper.mappear(quoteItemService.encontrarPeloId(itemId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{quoteId}/items/{itemId}")
    public ResponseEntity<?> updateQuoteItem(
//            @RequestHeader Long id,
            @PathVariable Long itemId,
            @RequestBody QuoteItemReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/quotes/id/items/id"));
        quoteItemService.atualizar(itemId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{quoteId}/items/{itemId}")
    public ResponseEntity<?> deleteQuoteItem(
//            @RequestHeader Long id
            @PathVariable Long itemId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/quotes/id/items/id"));
        quoteItemService.deletar(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
