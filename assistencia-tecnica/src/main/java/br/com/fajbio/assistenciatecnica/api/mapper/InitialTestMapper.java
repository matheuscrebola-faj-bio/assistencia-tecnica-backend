package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.InitialTestReq;
import br.com.fajbio.assistenciatecnica.api.dto.InitialTestRes;
import br.com.fajbio.assistenciatecnica.domain.model.InitialTest;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitialTestMapper {
    private final UserMapper userMapper;

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

    public InitialTestRes mappear(InitialTest initialTest) {
        return InitialTestRes.builder()
                .id(initialTest.getId())
                .tecnico(userMapper.mappear(initialTest.getTecnico()))
                .aparelho(initialTest.getAparelho())
                .criadoEm(initialTest.getCriadoEm())
                .valores(initialTest.getValores())
                .resultado(initialTest.getResultado())
                .build();
    }
}
