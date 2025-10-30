package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.SoIntakeReq;
import br.com.fajbio.assistenciatecnica.api.dto.SoIntakeRes;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.SoIntake;
import org.springframework.stereotype.Component;

@Component
public class SoIntakeMapper {
    public SoIntake mappear(SoIntakeReq req, ServiceOrder service) {
        return SoIntake.builder()
                .serviceOrderId(service.getId())
                .serviceOrder(service)
                .dataChegada(req.dataChegada())
                .lacreIntacto(req.lacreIntacto())
                .observacoes(req.observacoes())
                .build();
    }

    public SoIntakeRes mappear(SoIntake soIntake) {
        return SoIntakeRes.builder()
                .id(soIntake.getId())
                .dataChegada(soIntake.getDataChegada())
                .lacreIntacto(soIntake.getLacreIntacto())
                .observacoes(soIntake.getObservacoes())
                .build();
    }
}
