package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import br.com.fajbio.assistenciatecnica.domain.enums.EOrigin;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.repository.ServiceOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceOrderMapper {
    private final ServiceOrderRepository repository;

    public ServiceOrder mappear(ServiceOrderReq req, Customer customer, Equipment equipment) {
        return ServiceOrder.builder()
                .atendimento(atendimento().toString())
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

    protected StringBuilder atendimento() {
        int ano = LocalDate.now().getYear();
        int mes = LocalDate.now().getMonthValue();

        // Busca o último registro (pode retornar null no início do mês)
        Short ultimoValor = repository.findUltimoValorOrderByUltimoValorDesc(mes, ano);
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

    public ServiceOrder mappear(ServiceOrder serviceOrder, SoStatusHistory soStatusHistory) {
        List<SoStatusHistory> historias = serviceOrder.getStatusHistory();
        historias.addLast(soStatusHistory);
        return ServiceOrder.builder()
                .id(serviceOrder.getId())
                .atendimento(serviceOrder.getAtendimento())
                .customerId(serviceOrder.getCustomerId())
                .customer(serviceOrder.getCustomer())
                .equipmentId(serviceOrder.getEquipmentId())
                .equipment(serviceOrder.getEquipment())
                .currentStatus(ESoStatus.TESTES_INICIAIS)
                .origin(serviceOrder.getOrigin())
                .requesterContato(serviceOrder.getRequesterContato())
                .requesterEmail(serviceOrder.getRequesterEmail())
                .requesterCompanyName(serviceOrder.getRequesterCompanyName())
                .requesterAddress(serviceOrder.getRequesterAddress())
                .productLine(serviceOrder.getProductLine())
                .criadoEm(serviceOrder.getCriadoEm())
                .statusHistory(historias)
                .build();
    }
}
