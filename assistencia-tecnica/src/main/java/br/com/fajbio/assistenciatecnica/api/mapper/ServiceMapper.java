package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.QuoteItemReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceMapper {

    public List<String> mappear(List<QuoteItemReq> quoteItemReqs) {
        return quoteItemReqs.stream()
                .map(QuoteItemReq::serviceName)
                .toList();
    }
}
