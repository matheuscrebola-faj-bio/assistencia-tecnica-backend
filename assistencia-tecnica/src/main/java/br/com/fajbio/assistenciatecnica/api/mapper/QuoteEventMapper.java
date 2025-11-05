package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.domain.enums.EQuoteEvent;
import br.com.fajbio.assistenciatecnica.domain.model.Quote;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QuoteEventMapper {
    public QuoteEvent mappear(Quote quote, EQuoteEvent tipo) {
        return QuoteEvent.builder()
                .quoteId(quote.getId())
                .quote(quote)
                .tipo(tipo)
                .userId(quote.getCreatedByUserId())
                .user(quote.getCreatedBy())
                .dataHora(LocalDateTime.now())
                .build();
    }
}
