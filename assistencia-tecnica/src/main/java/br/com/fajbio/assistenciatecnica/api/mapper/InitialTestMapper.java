package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.InitialTestReq;
import br.com.fajbio.assistenciatecnica.domain.model.InitialTest;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitialTestMapper {

    public InitialTest mappear(ServiceOrder serviceOrder, InitialTestReq req) {
        return InitialTest.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .tecnicoId(serviceOrder.getAssignedToUserId())
                .tecnico(serviceOrder.getAssignedTo())
                .aparelho(req.aparelho())
                .valores(req.valores())
                .criadoEm(LocalDateTime.now())
                .resultado(req.resultado())
                .build();
    }
}
