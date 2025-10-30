package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.SoStatusHistoryRes;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatusHistory;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SoStatusHistoryMapper {
    private final SoStatusMapper soStatusMapper;

    public SoStatusHistory mappear(ServiceOrder serviceOrder, SoStatus from, SoStatus to, User user){
        return SoStatusHistory.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .fromStatusId(from.getId())
                .fromStatus(from)
                .toStatusId(to.getId())
                .toStatus(to)
                .changedByUserId(user.getId())
                .changedBy(user)
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public SoStatusHistory mappear(ServiceOrder serviceOrder, SoStatus soStatus, User user){
        return SoStatusHistory.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .fromStatusId(soStatus.getId())
                .fromStatus(soStatus)
                .changedByUserId(user.getId())
                .changedBy(user)
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public List<SoStatusHistoryRes> mappear(List<SoStatusHistory> soStatusHistories) {
        return soStatusHistories.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public SoStatusHistoryRes mappear(SoStatusHistory soStatusHistory) {
        return SoStatusHistoryRes.builder()
                .id(soStatusHistory.getId())
                .fromStatus(soStatusMapper.mappear(soStatusHistory.getFromStatus()))
                .toStatus(soStatusMapper.mappear(soStatusHistory.getToStatus()))
                .changedBy(soStatusHistory.getChangedBy().getUsername())
                .atualizadoEm(soStatusHistory.getAtualizadoEm())
                .build();
    }

}
