package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.SoStatusRes;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoStatusMapper {

    public SoStatus mappear(ESoStatus status) {
        return SoStatus.builder()
                .nome(status)
                .build();
    }

    public SoStatusRes mappear(SoStatus status){
        return SoStatusRes.builder()
                .id(status.getId())
                .nome(status.getNome())
                .build();
    }

    public List<SoStatusRes> mappear(List<SoStatus> soStatuses) {
        return soStatuses.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }
}
