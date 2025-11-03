package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.WorkOrderReq;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import br.com.fajbio.assistenciatecnica.domain.model.WorkOrder;
import br.com.fajbio.assistenciatecnica.domain.repository.WorkOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkOrderService {
    private final WorkOrderRepository repository;

    @Transactional
    protected WorkOrder salvar(WorkOrder workOrder){
        return repository.save(workOrder);
    }

    public WorkOrder cadastrar(WorkOrder workOrder) {
        return salvar(workOrder);
    }

    @Transactional
    public WorkOrder adicionarWorkLog(WorkOrder workOrder, WorkLog workLog) {
        workOrder.getLogs().add(workLog);
        return workOrder;
    }

    public WorkOrder encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizar(Long id, WorkLog log, WorkOrderReq req) {
        WorkOrder order = encontrarPeloId(id);
        order.getLogs().add(log);
        order.setObservacoes(req.observacoes());

    }
    @Transactional
    public void atualizar(Long id, WorkLog log, WorkOrderReq req, LocalDateTime agora) {
        WorkOrder order = encontrarPeloId(id);
        order.getLogs().add(log);
        order.setObservacoes(req.observacoes());
        order.setDataFim(agora);
    }
}
