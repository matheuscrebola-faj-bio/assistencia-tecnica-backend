package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.WorkLogReq;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.model.WorkOrder;
import org.springframework.stereotype.Component;

@Component
public class WorkLogMapper {

    public WorkLog mappear(WorkOrder workOrder, WorkLogReq worklog) {
        return WorkLog.builder()
                .workOrderId(workOrder.getId())
                .workOrder(workOrder)
                .evento(worklog.evento())
                .build();
    }
}
