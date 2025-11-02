package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderUpdateReq;
import br.com.fajbio.assistenciatecnica.domain.enums.EOrigin;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.repository.ServiceOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

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

    @Transactional
    public ServiceOrder cadastrarNovaOrdem(ServiceOrderReq req, Customer customer, Equipment equipment,
                                           Function<Short, String> criarAtendimento) {
        // delimita mês atual
        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioMes = hoje.withDayOfMonth(1).atStartOfDay();
        LocalDateTime inicioProxMes = hoje.withDayOfMonth(1).plusMonths(1).atStartOfDay();

        // busca o último valor (0 se ainda não houver)
        Short ultimo = repository.findMaxUltimoValorNoMes(inicioMes, inicioProxMes);

        // incrementa com segurança de tipo
        short proximo = (short) (ultimo == null ? 1 : (ultimo + 1));

        ServiceOrder nova = ServiceOrder.builder()
                .atendimento(criarAtendimento.apply(proximo)) // seu método/func para montar a string
                .ultimoValor(proximo)
                .customerId(customer.getId())
                .customer(customer)
                .equipmentId(equipment.getId())
                .equipment(equipment)
                .currentStatus(ESoStatus.AGUARDANDO_RECEBIMENTO)
                .origin(EOrigin.WEB_FORM)
                .requesterContato(req.contato())
                .requesterEmail(req.email())
                .requesterCompanyName(req.empresa())
                .requesterAddress(req.endereco())
                .productLine(req.produto())
                .criadoEm(LocalDateTime.now())
                .build();

        return repository.save(nova);
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

    public List<ServiceOrder> encontrarPeloStatusAtual(ESoStatus eSoStatus) {
        return repository.findByCurrentStatus(eSoStatus);
    }
//
//    @Transactional
//    public void atualizarCamposDoAtendimento(Long serviceOrderId, ServiceOrderUpdateReq req) {
//        ServiceOrder serviceOrder = encontrarPeloId(serviceOrderId);
//        serviceOrder.setEm
//    }

//    @Transactional
//    public ServiceOrder registrarChegada(Long id, SoIntakeReq req) {
//        var serviceOrder = encontrarPeloId(id);
//
//    }

//    public List<ServiceOrdersRes> encontrarPeloStatusAtual(ESoStatus status) {
//        return repository.findAllByCurrentStatus(status);
//    }
}
