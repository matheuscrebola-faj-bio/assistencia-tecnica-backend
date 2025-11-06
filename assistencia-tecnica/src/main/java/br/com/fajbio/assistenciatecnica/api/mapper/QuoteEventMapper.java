package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.QuoteEventRes;
import br.com.fajbio.assistenciatecnica.domain.enums.EQuoteEvent;
import br.com.fajbio.assistenciatecnica.domain.model.Quote;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteEvent;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public QuoteEvent mappear(Quote quote, EQuoteEvent tipo, User user) {
        return QuoteEvent.builder()
                .quoteId(quote.getId())
                .quote(quote)
                .tipo(tipo)
                .userId(user.getId())
                .user(user)
                .dataHora(LocalDateTime.now())
                .build();
    }

    public QuoteEventRes mappear(QuoteEvent quoteEvent){
        return QuoteEventRes.builder()
                .id(quoteEvent.getQuoteId())
                .tipo(quoteEvent.getTipo())
                .dataHora(quoteEvent.getDataHora())
                .build();
    }

    public List<QuoteEventRes> mappear(Quote quote){
        List<QuoteEvent> quoteEvents = quote.getEvents();
        return quoteEvents.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }
}
