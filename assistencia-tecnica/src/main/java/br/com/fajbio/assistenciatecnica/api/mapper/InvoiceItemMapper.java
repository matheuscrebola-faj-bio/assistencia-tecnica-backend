package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.InvoiceItemReq;
import br.com.fajbio.assistenciatecnica.api.dto.InvoiceItemRes;
import br.com.fajbio.assistenciatecnica.domain.model.Invoice;
import br.com.fajbio.assistenciatecnica.domain.model.InvoiceItem;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceItemMapper {

    public InvoiceItemRes mappear(InvoiceItem item){
        return InvoiceItemRes.builder()
                .id(item.getId())
                .descricao(item.getDescricao())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitario())
                .build();
    }

    public List<InvoiceItemRes> mappear(Invoice invoice) {
        List<InvoiceItem> invoiceItems = invoice.getItems();
        return invoiceItems.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public InvoiceItem mappear(QuoteItem item, Invoice invoice, InvoiceItemReq req) {
        return InvoiceItem.builder()
                .invoiceId(invoice.getId())
                .invoice(invoice)
                .quoteItemId(item.getQuoteId())
                .quoteItem(item)
                .descricao(req.descricao())
                .quantidade(req.quantidade())
                .precoUnitario(req.precoUnitario())
                .build();
    }

}
