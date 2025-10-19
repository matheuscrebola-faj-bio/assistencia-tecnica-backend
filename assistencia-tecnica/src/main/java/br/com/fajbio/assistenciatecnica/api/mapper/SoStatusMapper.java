package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatus;
import org.springframework.stereotype.Component;

@Component
public class SoStatusMapper {

    public SoStatus mappear() {
        return SoStatus.builder()
                .nome(ESoStatus.AGUARDANDO_RECEBIMENTO)
                .build();
    }
}
