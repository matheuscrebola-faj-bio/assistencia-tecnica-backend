package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import br.com.fajbio.assistenciatecnica.domain.enums.EOrigin;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.repository.ServiceOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ServiceOrderMapper {
    private final ServiceOrderRepository repository;

    public ServiceOrder mappear(ServiceOrderReq req, Customer customer, Equipment equipment) {
        return ServiceOrder.builder()
                .atendimento(criarAtentimento().toString())
                .customerId(customer.getId())
                .customer(customer)
                .equipmentId(equipment.getId())
                .equipment(equipment)
                .currentStatus(ESoStatus.AGUARDANDO_RECEBIMENTO)
                .origin(EOrigin.WEB_FORM)
                .requesterContato(req.contato())
                .requesterEmail(req.email())
                .requesterCompanyName(req.empresa())
                .requesterAddress(req.endereco())
                .productLine(req.produto())
                .criadoEm(LocalDateTime.now())
                .build();
    }

    protected StringBuilder criarAtentimento() {
        int ano = LocalDate.now().getYear();
        int mes = LocalDate.now().getMonthValue();
        // Busca o último registro (pode retornar null no início do mês)
        Short ultimoValor = repository.findUltimoValor();
        short proximoValor;
        if (ultimoValor == null) {
            // Primeiro atendimento do mês
            proximoValor = 0;
        } else {
            // Incrementa dentro do mesmo mês
            proximoValor = (short) (ultimoValor + 1);
        }
        // Formata o número com 3 dígitos (000, 001, ...)
        String codigo = String.format("%d%02d%03d", ano, mes, proximoValor);
        return new StringBuilder(codigo);
    }
}
