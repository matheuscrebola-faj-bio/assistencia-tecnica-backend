package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.*;
import br.com.fajbio.assistenciatecnica.api.mapper.*;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.*;
import br.com.fajbio.assistenciatecnica.domain.service.*;
import br.com.fajbio.assistenciatecnica.infra.email.EmailBodies;
import br.com.fajbio.assistenciatecnica.infra.email.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/service-orders")
@RequiredArgsConstructor
public class ServiceOrdersController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final ServiceOrderService serviceOrderService;
    private final ServiceOrderMapper serviceOrderMapper;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final EquipmentService equipmentService;
    private final SoStatusService soStatusService;
    private final SoDocumentService soDocumentService;
    private final SoDocumentMapper soDocumentMapper;
    private final NotificationMapper notificationMapper;
    private final NotificationService notificationService;
    private final DocTemplateFillService docTemplateFillService;
//    private final MailService mailService;
    private final DocumentService documentService;
    private final SoStatusHistoryMapper soStatusHistoryMapper;
    private final SoStatusHistoryService soStatusHistoryService;
    private final UserService userService;
    private final SoIntakeMapper soIntakeMapper;
    private final SoIntakeService soIntakeService;
    private final InitialTestService initialTestService;
    private final InitialTestMapper initialTestMapper;
    private final QuoteMapper quoteMapper;
    private final QuoteService quoteService;
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;
    private final WorkOrderMapper workOrderMapper;
    private final WorkOrderService workOrderService;
    private final WorkLogMapper workLogMapper;
    private final WorkLogService workLogService;

//    @GetMapping
//    public ResponseEntity<?> listServiceOrders(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders"));
//        //TODO: lista atendimentos com filtros (status, cliente, técnico).
//        return null;
//    }
//
//    @GetMapping
//    public ResponseEntity<?> listServiceOrders(@RequestHeader Long userId, ESoStatus eSoStatus){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/service-orders"));
//        //List<ServiceOrdersRes> res = serviceOrderService.encontrarPeloStatusAtual(eSoStatus);
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }

//    @PostMapping("/public")
//    public ResponseEntity<?> createServiceOrder(@RequestBody ServiceOrderReq req) throws Exception {
//        // 1) Cria OS e registros relacionados
//        var customer = customerService.encontrarPeloDocumento(req.cnpj());
//        var equipment = equipmentService.encontrarPeloCustomerId(customer.getId());
//        var serviceOrder = serviceOrderMapper.mappear(req, customer, equipment);
//        var service = serviceOrderService.cadastrar(serviceOrder);
//        customerService.adicionarOrdemServico(customerMapper.mappear(customer, service));
//
//        SoDocument document = soDocumentMapper.mappear(service);
//
//        // 2) Preenche DOCX (telefone = req.contato(), modelo = tipoDoc)
//        Path docx = docTemplateFillService.preencherServiceOrder(
//                req,
//                req.contato(),                    // telefone
//                document.getTipoDoc().toString() // modelo do documento
//        );
//
//        // 3) Converte para PDF
//        Path pdf = documentService.gerarPdfFromDocx(docx);
//
//        // 4) Persiste metadados do documento com o caminho físico do PDF
//        soDocumentService.cadastrar(soDocumentMapper.mappear(document, pdf));
//
//        // 5) Cria notificação (como você já faz)
//        notificationService.cadastrar(notificationMapper.mappear(service, req));
//
//        // 6) Envia e-mail para o contato informado no formulário
//        String assunto = "Instruções de envio do equipamento — Ordem de Serviço";
//        String corpoHtml = EmailBodies.instrucoesEnvioHtml();
//        mailService.enviarComAnexo(
//                req.email(),     // destinatário
//                assunto,
//                corpoHtml,
//                pdf              // anexo PDF
//        );
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getServiceOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id"));
//        //TODO: detalhe do atendimento (campos principais).
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateServiceOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/service-orders/id"));
//        //TODO: atualiza campos do atendimento.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteServiceOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/service-orders/id"));
//        //TODO: cancela/Remove (conforme regra de negócio).
//        return null;
//    }
//
//    @GetMapping("/{id}/timeline")
//    public ResponseEntity<?> getServiceOrderTimeline(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/timeline"));
//        //TODO: consolida e retorna linha do tempo (histórico de status e eventos).
//        return null;
//    }
//
//    @PostMapping("/{id}/assign/{userId}")
//    public ResponseEntity<?> assignServiceOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/assign/id"));
//        //TODO: atribui técnico e atualiza status conforme regra.
//        return null;
//    }
//
//    @GetMapping("/{id}/status/history")
//    public ResponseEntity<?> listStatusHistory(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/status/history"));
//        //TODO: retorna histórico de mudanças de status (audit trail).
//        return null;
//    }
//
//    @PostMapping("/{id}/status")
//    public ResponseEntity<?> changeStatus(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/status"));
//        //TODO: valida transição, grava histórico e atualiza status atual.
//        return null;
//    }
//
//    @GetMapping("/{id}/intake")
//    public ResponseEntity<?> getIntake(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/intake"));
//        //TODO: retorna dados de recebimento (data, lacre, observações).
//        return null;
//    }
//
    @PostMapping("/{id}/intake")
    public ResponseEntity<?> createIntake(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody SoIntakeReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/service-orders/id/intake"));
        //TODO: registra chegada; muda status para triagem; pode notificar administrativo.
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        var recebimento = soStatusService.encontrarPeloNome(ESoStatus.AGUARDANDO_RECEBIMENTO);
        var testes = soStatusService.encontrarPeloNome(ESoStatus.TESTES_INICIAIS);
        User user = userService.encontrarPeloId(userId);
        SoStatusHistory soStatusHistory = soStatusHistoryService.cadastrar(soStatusHistoryMapper.mappear(serviceOrder, recebimento, testes, user));
        var service = serviceOrderService.cadastrar(serviceOrderMapper.mappear(serviceOrder, soStatusHistory));
        soIntakeService.cadastrar(soIntakeMapper.mappear(req, service));
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @PutMapping("/{id}/intake/{intakeId}")
//    public ResponseEntity<?> updateIntake(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/service-orders/id/intake/id"));
//        //TODO: atualiza registro de recebimento.
//        return null;
//    }
//
//    @GetMapping("/{id}/initial-tests")
//    public ResponseEntity<?> listInitialTests(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/initial-tests"));
//        //TODO: lista testes iniciais do atendimento.
//        return null;
//    }
//
    @PostMapping("/{id}/initial-tests")
    public ResponseEntity<?> createInitialTest(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody InitialTestReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/service-orders/id/initial-tests"));
        //TODO: cria teste inicial (tecn. responsável, período, resultado).
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        initialTestService.cadastrar(initialTestMapper.mappear(serviceOrder, req));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//
//    @GetMapping("/{id}/initial-tests/{testId}")
//    public ResponseEntity<?> getInitialTest(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/initial-tests/id"));
//        //TODO: detalhe do teste.
//        return null;
//    }
//
//    @PutMapping("/{id}/initial-tests/{testId}")
//    public ResponseEntity<?> updateInitialTest(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/service-orders/id/initial-tests/id"));
//        //TODO: atualiza teste.
//        return null;
//    }
//
//    @DeleteMapping("/{id}/initial-tests/{testId}")
//    public ResponseEntity<?> deleteInitialTest(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/service-orders/id/initial-tests/id"));
//        //TODO: remove teste.
//        return null;
//    }
//
//    @GetMapping("/{id}/calibrations")
//    public ResponseEntity<?> listCalibrations(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/calibrations"));
//        //TODO: lista calibrações da OS.
//        return null;
//    }
//
//    @PostMapping("/{id}/calibrations")
//    public ResponseEntity<?> createCalibration(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/calibrations"));
//        //TODO: cria calibração e pontos (em transação).
//        return null;
//    }
//
//    @GetMapping("/{id}/checklists")
//    public ResponseEntity<?> listChecklists(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/checklists"));
//        //TODO: lista checklists preenchidos.
//        return null;
//    }
//
//    @PostMapping("/{id}/checklists")
//    public ResponseEntity<?> createChecklist(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/checklists"));
//        //TODO: cria checklist a partir de um modelo e salva respostas.
//        return null;
//    }
//
//    @GetMapping("/{id}/closeout")
//    public ResponseEntity<?> getCloseout(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/closeout"));
//        //TODO: retorna dados de fechamento (testes finais, data, observações).
//        return null;
//    }
//
//    @PostMapping("/{id}/closeout")
//    public ResponseEntity<?> createCloseout(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/closeout"));
//        //TODO: registra fechamento da OS e prepara geração de documentos.
//        return null;
//    }
//
//    @PutMapping("/{id}/closeout")
//    public ResponseEntity<?> updateCloseout(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/service-orders/id/closeout"));
//        //TODO: atualiza fechamento.
//        return null;
//    }
//
//    @GetMapping("/{id}/quotes")
//    public ResponseEntity<?> listQuotesByServiceOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/quotes"));
//        //TODO: lista orçamentos da OS (com status).
//        return null;
//    }
//
    @PostMapping("/{id}/quotes")
    public ResponseEntity<?> createQuote(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody QuoteReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/service-orders/id/quotes"));
        //TODO: cria orçamento (itens, totais) e mantém como rascunho.
        User user = userService.encontrarPeloId(userId);
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        Quote quote = quoteMapper.mappear(user, serviceOrder);
        List<String> serviceNames = serviceMapper.mappear(req.quoteItemReq());
        List<Service> service = serviceService.encontrarTodosPeloNome(serviceNames);
        List<QuoteItem> quoteItem = quoteService.cadastrar(quoteMapper.mappear(quote,req,service));
        Quote newQuote = quoteService.cadastrar(quoteMapper.mappear(quoteItem, quote));
        ServiceOrder newServiceOrder = serviceOrderMapper.mappear(newQuote, serviceOrder);
        SoStatus soStatus = soStatusService.encontrarPeloNome(ESoStatus.VALIDAR_ORACAMENTO);
        serviceOrderService.cadastrar(serviceOrderMapper.mappear(newServiceOrder, soStatusHistoryMapper.mappear(newServiceOrder, soStatus, user)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//
//    @GetMapping("/{id}/work-orders")
//    public ResponseEntity<?> listWorkOrders(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/work-orders"));
//        //TODO: lista ordens de trabalho da OS.
//        return null;
//    }
//
    @PostMapping("/{id}/work-orders")
    public ResponseEntity<?> createWorkOrder(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody WorkOrderReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/service-orders/id/work-orders"));
        //TODO: cria ordem de trabalho (início do reparo).
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        WorkOrder workOrder = workOrderService.cadastrar(workOrderMapper.mappear(serviceOrder, req));
        WorkLog workLog = workLogService.cadastrar(workLogMapper.mappear(workOrder, req.worklog()));
        WorkOrder newWorkOrder = workOrderService.cadastrar(workOrderMapper.mappear(workOrder, workLog));
        serviceOrderService.cadastrar(serviceOrderMapper.mappear(serviceOrder, newWorkOrder));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("/{id}/documents")
//    public ResponseEntity<?> listDocuments(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/documents"));
//        //TODO: lista documentos anexados/gerados.
//        return null;
//    }
//
//    @PostMapping("/{id}/documents")
//    public ResponseEntity<?> createDocument(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/documents"));
//        //TODO: anexa documento (upload/registro de link).
//        return null;
//    }
//
//    @PostMapping("/{id}/documents/generate/fat")
//    public ResponseEntity<?> generateFatDocument(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/documents/generate/fat"));
//        //TODO: compõe dados (intake, testes, orçamento, closeout), gera .docx, renderiza .pdf, salva em repositório e retorna link; opcionalmente envia por email.
//        return null;
//    }
//
//    @GetMapping("/{id}/invoices")
//    public ResponseEntity<?> listInvoicesByServiceOrder(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/invoices"));
//        //TODO: lista faturas relacionadas à OS.
//        return null;
//    }
//
//    @PostMapping("/{id}/invoices")
//    public ResponseEntity<?> createInvoice(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/invoices"));
//        //TODO: gera fatura a partir do orçamento aprovado (itens, totais).
//        return null;
//    }
//
//    @GetMapping("/{id}/shipments")
//    public ResponseEntity<?> listShipments(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/shipments"));
//        //TODO: lista envios da OS.
//        return null;
//    }

    @PostMapping("/{id}/shipments")
    public ResponseEntity<?> createShipment(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/shipments"));
        //TODO: cria envio, grava código de rastreio e dispara notificação ao cliente.

        return null;
    }

//    @GetMapping("/{id}/notifications")
//    public ResponseEntity<?> listNotifications(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/notifications"));
//        //TODO: lista notificações emitidas/pendentes da OS.
//        return null;
//    }
//
//    @PostMapping("/{id}/notifications")
//    public ResponseEntity<?> createNotification(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/notifications"));
//        //TODO: envia notificação (email/SMS) usando template e parâmetros; registra status/retentativas.
//        return null;
//    }
}
