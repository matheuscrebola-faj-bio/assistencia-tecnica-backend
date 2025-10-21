package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.QuoteItemReq;
import br.com.fajbio.assistenciatecnica.api.dto.QuoteReq;
import br.com.fajbio.assistenciatecnica.domain.enums.EQuoteStatus;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class QuoteMapper {
    public Quote mappear(User user, ServiceOrder serviceOrder) {
        return Quote.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .status(EQuoteStatus.CRIADA)
                .validade(LocalDate.now().plusMonths(3))
                .createdByUserId(user.getId())
                .createdBy(user)
                .criadoEm(LocalDateTime.now())
                .build();
    }

    public List<QuoteItem> mappear(Quote quote, QuoteReq req, List<Service> services) {
        if (quote == null || quote.getId() == null) {
            throw new IllegalArgumentException("Quote e/ou quoteId não podem ser nulos.");
        }
        if (services == null || services.isEmpty()) {
            return List.of();
        }

        final String descricao = (req != null) ? req.descricao() : null;

        return services.stream()
                .map(svc -> QuoteItem.builder()
                        .quoteId(quote.getId())   // precisa setar a FK pois @JoinColumn é insertable=false
                        .quote(quote)             // útil para já manter a associação em memória
                        .serviceId(svc.getId())   // idem para a FK de service
                        .service(svc)
                        .descricao(descricao)
                        .build()
                )
                .toList();
    }

}
