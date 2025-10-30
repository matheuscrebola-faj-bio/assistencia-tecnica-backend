package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.WorkLogReq;
import br.com.fajbio.assistenciatecnica.api.dto.WorkLogRes;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.model.WorkOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkLogMapper {

    public WorkLog mappear(WorkOrder workOrder, WorkLogReq worklog) {
        return WorkLog.builder()
                .workOrderId(workOrder.getId())
                .workOrder(workOrder)
                .evento(worklog.evento())
                .build();
    }

    public List<WorkLogRes> mappear(List<WorkLog> workLogs) {
        return workLogs.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public WorkLogRes mappear(WorkLog workLog){
        return WorkLogRes.builder()
                .id(workLog.getId())
                .evento(workLog.getEvento())
                .build();
    }
}
