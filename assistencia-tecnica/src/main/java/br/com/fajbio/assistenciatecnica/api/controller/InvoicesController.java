package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.*;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.InvoiceItemMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.InvoiceMapper;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.InvoiceItemService;
import br.com.fajbio.assistenciatecnica.domain.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoicesController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceItemMapper invoiceItemMapper;
    private final InvoiceItemService invoiceItemService;

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceRes> getInvoice(
//            @RequestHeader Long id,
            @PathVariable Long invoiceId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/invoices/id"));
        //TODO: detalhe da fatura.
        InvoiceRes res = invoiceMapper.mappear(invoiceService.encontrarPeloId(invoiceId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<?> updateInvoice(
//            @RequestHeader Long id
            @PathVariable Long invoiceId,
            @RequestBody InvoiceReq req
        ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/invoices/id"));
        //TODO: atualiza metadados da fatura.
        invoiceService.atualizar(invoiceId,req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/{invoiceId}/issue")
//    public ResponseEntity<?> issueInvoice(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/issue"));
//        //TODO: atribui número, emite NF/notas, muda status e anexa PDF/XML.
//        return null;
//    }

    @PostMapping("/{invoiceId}/cancel")
    public ResponseEntity<?> cancelInvoice(
//            @RequestHeader Long id,
            @PathVariable Long invoiceId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/cancel"));
        //TODO: cancela a fatura.
        invoiceService.atualizar(invoiceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{invoiceId}/pay")
    public ResponseEntity<?> payInvoice(
//            @RequestHeader Long id,
            @PathVariable Long invoiceId,
            @RequestBody PagamentoReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/pay"));
        //TODO: registra pagamento (parcial/total) e atualiza status.
        invoiceService.atualizar(invoiceId,req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{invoiceId}/items")
    public ResponseEntity<List<InvoiceItemRes>> listInvoiceItems(
//            @RequestHeader Long id,
            @PathVariable Long invoiceId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/invoices/id/items"));
        //TODO: lista itens da fatura.
        List<InvoiceItemRes> res = invoiceItemMapper.mappear(invoiceService.encontrarPeloId(invoiceId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{invoiceId}/items")
    public ResponseEntity<?> createInvoiceItem(
//            @RequestHeader Long id,
            @PathVariable Long invoiceId,
            @RequestBody InvoiceItemReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/invoices/id/items"));
        //TODO: adiciona item à fatura.
        Invoice invoice = invoiceService.encontrarPeloId(invoiceId);
        ServiceOrder serviceOrder = invoice.getServiceOrder();
        List<Quote> quotes = serviceOrder.getQuotes();
        List<QuoteItem> quoteItems = quotes.getLast().getItems();
        InvoiceItem item = invoiceItemService.cadastrar(
                invoiceItemMapper.mappear(quoteItems.getLast(),invoice,req));
        invoiceService.atualizar(invoiceId,item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{invoiceId}/items/{itemId}")
    public ResponseEntity<InvoiceItemRes> getInvoiceItem(
//            @RequestHeader Long id,
            @PathVariable Long itemId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/invoices/id/items/id"));
        //TODO: detalhe do item.
        InvoiceItemRes res = invoiceItemMapper.mappear(invoiceItemService.encontrarPeloId(itemId));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{invoiceId}/items/{itemId}")
    public ResponseEntity<?> updateInvoiceItem(
//            @RequestHeader Long id,
            @PathVariable Long itemId,
            @RequestBody InvoiceItemReq req
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/invoices/id/items/id"));
        invoiceItemService.atualizar(itemId, req);
        //TODO: atualiza item.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{invoiceId}/items/{itemId}")
    public ResponseEntity<?> deleteInvoiceItem(
//            @RequestHeader Long id,
            @PathVariable Long itemId
            ){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/invoices/id/items/id"));
        //TODO: remove item.
        invoiceItemService.deletar(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
