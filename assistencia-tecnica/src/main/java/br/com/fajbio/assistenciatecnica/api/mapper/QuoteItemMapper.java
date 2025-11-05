package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.QuoteItemReq;
import br.com.fajbio.assistenciatecnica.domain.model.Quote;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteItem;
import br.com.fajbio.assistenciatecnica.domain.model.Service;
import org.springframework.stereotype.Component;

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
}
