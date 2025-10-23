package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.WorkOrderReq;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.model.WorkOrder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class WorkOrderMapper {

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
}
