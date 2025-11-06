package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.InvoiceReq;
import br.com.fajbio.assistenciatecnica.api.dto.PagamentoReq;
import br.com.fajbio.assistenciatecnica.domain.enums.EInvoice;
import br.com.fajbio.assistenciatecnica.domain.model.Invoice;
import br.com.fajbio.assistenciatecnica.domain.model.InvoiceItem;
import br.com.fajbio.assistenciatecnica.domain.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository repository;

    public Invoice encontrarPeloId(Long invoiceId) {
        return repository.findById(invoiceId).orElse(null);
    }

    @Transactional
    public void atualizar(Long invoiceId, InvoiceReq req) {
        var invoice = encontrarPeloId(invoiceId);
        invoice.setValorTotal(req.valorTotal());
        invoice.setImpostos(req.impostos());
        invoice.setStatus(EInvoice.ATUALIZADA);
    }

    @Transactional
    public void atualizar(Long invoiceId) {
        var invoice = encontrarPeloId(invoiceId);
        invoice.setStatus(EInvoice.CANCELADA);
    }

    @Transactional
    public void atualizar(Long invoiceId, PagamentoReq req) {
        var invoice = encontrarPeloId(invoiceId);
        invoice.setStatus(req.invoice());
    }

    @Transactional
    public void atualizar(Long invoiceId, InvoiceItem item) {
        var invoice = encontrarPeloId(invoiceId);
        invoice.getItems().add(item);
    }
}
