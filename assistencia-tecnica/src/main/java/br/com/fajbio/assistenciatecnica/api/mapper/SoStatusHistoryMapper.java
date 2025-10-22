package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatusHistory;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SoStatusHistoryMapper {

    public SoStatusHistory mappear(ServiceOrder serviceOrder, SoStatus recebimento, SoStatus testes, User user){
        return SoStatusHistory.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .fromStatusId(recebimento.getId())
                .fromStatus(recebimento)
                .toStatusId(testes.getId())
                .toStatus(testes)
                .changedByUserId(user.getId())
                .changedBy(user)
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public SoStatusHistory mappear(ServiceOrder serviceOrder, SoStatus recebimento, User user){
        return SoStatusHistory.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .fromStatusId(recebimento.getId())
                .fromStatus(recebimento)
                .toStatusId(testes.getId())
                .toStatus(testes)
                .changedByUserId(user.getId())
                .changedBy(user)
                .atualizadoEm(LocalDateTime.now())
                .build();
    }
}
