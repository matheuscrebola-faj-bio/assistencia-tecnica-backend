package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.repository.ServiceOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {
    private final ServiceOrderRepository repository;

    @Transactional
    protected ServiceOrder salvar(ServiceOrder serviceOrder) {
        return repository.save(serviceOrder);
    }

    public ServiceOrder cadastrar(ServiceOrder serviceOrder) {
        return salvar(serviceOrder);
    }

    public ServiceOrder encontrarPeloId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public void registrarChegada(ServiceOrder serviceOrder, SoStatusHistory statusHistory) {
        serviceOrder.getStatusHistory().add(statusHistory);
    }

    @Transactional
    public void adicionarQuote(Quote quote, ServiceOrder serviceOrder) {
        serviceOrder.getQuotes().add(quote);
    }

    @Transactional
    public void atualizarStatusHistory(ServiceOrder serviceOrder, SoStatusHistory statusHistory) {
        serviceOrder.getStatusHistory().add(statusHistory);
    }

    @Transactional
    public void adicionarWorkOrder(ServiceOrder serviceOrder, WorkOrder workOrder) {
        serviceOrder.getWorkOrders().add(workOrder);
    }

    public List<WorkOrder> encontrarWorkOrders(Long serviceOrderId) {
        ServiceOrder serviceOrder = encontrarPeloId(serviceOrderId);
        return serviceOrder.getWorkOrders();
    }

    public Short encontrarUltimoValor(){
        return repository.findUltimoValor();
    }

    @Transactional
    public void exclusaoLogica(Long serviceOrderId) {
        ServiceOrder serviceOrder = encontrarPeloId(serviceOrderId);
        serviceOrder.setClosedAt(LocalDateTime.now());
        serviceOrder.setAtivo(false);
    }

    public List<SoStatusHistory> encontrarHistoriaPeloId(Long serviceOrderId) {
        ServiceOrder serviceOrder = encontrarPeloId(serviceOrderId);
        return serviceOrder.getStatusHistory();
    }

    @Transactional
    public ServiceOrder atribuirTecnico(Long serviceOrderId, User user) {
        ServiceOrder serviceOrder = encontrarPeloId(serviceOrderId);
        serviceOrder.setAssignedTo(user);
        serviceOrder.setAssignedToUserId(user.getId());
        return serviceOrder;
    }

    @Transactional
    public void atualizarStatusHistory(Long serviceOrderId, SoStatusHistory statusHistory) {
        ServiceOrder serviceOrder = encontrarPeloId(serviceOrderId);
        serviceOrder.getStatusHistory().add(statusHistory);
    }

//    @Transactional
//    public ServiceOrder registrarChegada(Long id, SoIntakeReq req) {
//        var serviceOrder = encontrarPeloId(id);
//
//    }

//    public List<ServiceOrdersRes> encontrarPeloStatusAtual(ESoStatus status) {
//        return repository.findAllByCurrentStatus(status);
//    }
}
