package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.QuoteItemReq;
import br.com.fajbio.assistenciatecnica.api.dto.QuoteItemRes;
import br.com.fajbio.assistenciatecnica.domain.model.Quote;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteItem;
import br.com.fajbio.assistenciatecnica.domain.model.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuoteItemMapper {

    public QuoteItem mappear(QuoteItemReq item, Quote quote, Service service) {
        return QuoteItem.builder()
                .quoteId(quote.getId())
                .quote(quote)
                .serviceId(service.getId())
                .service(service)
                .descricao(item.descricao())
                .build();
    }

    public QuoteItemRes mappear(QuoteItem quoteItem){
        return QuoteItemRes.builder()
                .id(quoteItem.getId())
                .descricao(quoteItem.getDescricao())
                .build();
    }

    public List<QuoteItemRes> mappear(Quote quote) {
        List<QuoteItem> quoteItems = quote.getItems();
        return quoteItems.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }
}
