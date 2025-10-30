package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.WorkOrderReq;
import br.com.fajbio.assistenciatecnica.api.dto.WorkOrderRes;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.model.WorkOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkOrderMapper {
    private final UserMapper userMapper;
    private final WorkLogMapper workLogMapper;

    public WorkOrder mappear(ServiceOrder serviceOrder, WorkOrderReq req) {
        return WorkOrder.builder()
                .serviceOrderId(serviceOrder.getId())
                .serviceOrder(serviceOrder)
                .tecnicoId(serviceOrder.getAssignedToUserId())
                .tecnico(serviceOrder.getAssignedTo())
                .dataInicio(LocalDateTime.now())
                .observacoes(req.observacoes())
                .build();
    }

    public WorkOrder mappear(WorkOrder workOrder, WorkLog workLog) {
        List<WorkLog> workLogs = workOrder.getLogs();
        workLogs.add(workLog);
        return WorkOrder.builder()
                .id(workOrder.getId())
                .serviceOrderId(workOrder.getServiceOrderId())
                .serviceOrder(workOrder.getServiceOrder())
                .tecnicoId(workOrder.getTecnicoId())
                .tecnico(workOrder.getTecnico())
                .dataInicio(workOrder.getDataInicio())
                .observacoes(workOrder.getObservacoes())
                .logs(workLogs)
                .build();
    }

    public List<WorkOrderRes> mappear(List<WorkOrder> workOrders) {
        return workOrders.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public WorkOrderRes mappear(WorkOrder workOrder){
        return WorkOrderRes.builder()
                .id(workOrder.getId())
                .tecnico(userMapper.mappear(workOrder.getTecnico()))
                .dataInicio(workOrder.getDataInicio())
                .dataFim(workOrder.getDataFim())
                .observacoes(workOrder.getObservacoes())
                .logs(workLogMapper.mappear(workOrder.getLogs()))
                .build();
    }
}
