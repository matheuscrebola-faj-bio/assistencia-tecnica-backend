package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.InvoiceRes;
import br.com.fajbio.assistenciatecnica.domain.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    public InvoiceRes mappear(Invoice invoice) {
        return InvoiceRes.builder()
                .id(invoice.getId())
                .numero(invoice.getNumero())
                .dataEmissao(invoice.getDataEmissao())
                .valorTotal(invoice.getValorTotal())
                .impostos(invoice.getImpostos())
                .status(invoice.getStatus())
                .build();
    }
}
