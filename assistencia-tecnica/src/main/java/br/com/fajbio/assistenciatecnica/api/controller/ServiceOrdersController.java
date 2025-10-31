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
    private final SoStatusMapper soStatusMapper;
    private final SoDocumentService soDocumentService;
    private final SoDocumentMapper soDocumentMapper;
    private final NotificationMapper notificationMapper;
    private final NotificationService notificationService;
    private final DocTemplateFillService docTemplateFillService;
//  private final MailService mailService;
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

    @GetMapping
    public ResponseEntity<List<ServiceOrderRes>> listServiceOrders(@RequestHeader Long userId, ESoStatus eSoStatus){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/service-orders"));
        // lista atendimentos com filtros (status, cliente, técnico).
        List<ServiceOrderRes> res = serviceOrderMapper.mappear(serviceOrderService.encontrarPeloStatusAtual(eSoStatus));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createServiceOrder(@RequestBody ServiceOrderReq req){
        var customer = customerService.encontrarPeloDocumento(req.cnpj());
        var equipment = equipmentService.encontrarPeloCustomerId(customer.getId());
        var ultimoValor = serviceOrderService.encontrarUltimoValor();
        var serviceOrder = serviceOrderService.cadastrar(serviceOrderMapper.mappear(req, customer, equipment, ultimoValor));
        customerService.adicionarOrdemServico(customer, serviceOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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
    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderRes> getServiceOrder(@RequestHeader Long userId, @PathVariable Long serviceOrderId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/service-orders/id"));
        // detalhe do atendimento (campos principais).
        ServiceOrderRes res = serviceOrderMapper.mappear(serviceOrderService.encontrarPeloId(serviceOrderId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateServiceOrder(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody ServiceOrderReq req){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/service-orders/id"));
//        //TODO: atualiza campos do atendimento.
//        serviceOrderService.atualizarCamposDoAtendimento(serviceOrderId, req);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceOrder(@RequestHeader Long userId, @PathVariable Long serviceOrderId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/service-orders/id"));
        // cancela/Remove (conforme regra de negócio).
        serviceOrderService.exclusaoLogica(serviceOrderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<List<SoStatusHistoryRes>> getServiceOrderTimeline(@RequestHeader Long userId, @PathVariable Long serviceOrderId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/service-orders/id/timeline"));
        // consolida e retorna linha do tempo (histórico de status e eventos).
        List<SoStatusHistoryRes> res = soStatusHistoryMapper.mappear(serviceOrderService.encontrarHistoriaPeloId(serviceOrderId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}/assign/{userId}")
    public ResponseEntity<?> assignServiceOrder(@RequestHeader Long id, @PathVariable Long serviceOrderId, @PathVariable Long userId){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/assign/id"));
        // atribui técnico e atualiza status conforme regra.
        var user = userService.encontrarPeloId(userId);
        var serviceOrder = serviceOrderService.atribuirTecnico(serviceOrderId, user);
        var fromStatus = soStatusService.encontrarPeloNome(serviceOrder.getCurrentStatus());
        var toStatus = soStatusService.encontrarPeloNome(ESoStatus.TESTES_INICIAIS);
        serviceOrderService.atualizarStatusHistory(serviceOrderId, soStatusHistoryMapper.mappear(serviceOrder, fromStatus, toStatus, user));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(@RequestHeader Long id, @PathVariable Long serviceOrderId, @PathVariable Long userId, @RequestBody StatusReq req){
        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/status"));
        // valida transição, grava histórico e atualiza status atual.
        var user = userService.encontrarPeloId(userId);
        var serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        var fromStatus = soStatusService.encontrarPeloNome(serviceOrder.getCurrentStatus());
        var toStatus = soStatusService.encontrarPeloNome(req.status());
        serviceOrderService.atualizarStatusHistory(serviceOrderId, soStatusHistoryMapper.mappear(serviceOrder, fromStatus, toStatus, user));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/intake")
    public ResponseEntity<SoIntakeRes> getIntake(@RequestHeader Long id, @PathVariable Long serviceOrderId){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/service-orders/id/intake"));
        // retorna dados de recebimento (data, lacre, observações).
        SoIntakeRes res = soIntakeMapper.mappear(soIntakeService.encontrarPeloIdDaServiceOrder(serviceOrderId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}/intake")
    public ResponseEntity<?> createIntake(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody SoIntakeReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/service-orders/id/intake"));
        // registra chegada; muda status para triagem; pode notificar administrativo.
        var serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        User user = userService.encontrarPeloId(userId);
        soIntakeService.cadastrar(soIntakeMapper.mappear(req, serviceOrder));
        SoStatus soStatus = soStatusService.cadastrar(soStatusMapper.mappear(ESoStatus.RECEBIDO));
        SoStatusHistory statusHistory = soStatusHistoryService.cadastrar(soStatusHistoryMapper.mappear(serviceOrder, soStatus, user));
        serviceOrderService.registrarChegada(serviceOrder,statusHistory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/intake/{intakeId}")
    public ResponseEntity<?> updateIntake(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @PathVariable Long intakeId, @RequestBody SoIntakeReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/service-orders/id/intake/id"));
        // atualiza registro de recebimento.
        soIntakeService.atualizarIntake(serviceOrderId, intakeId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/initial-tests")
    public ResponseEntity<?> listInitialTests(@RequestHeader Long userId, @PathVariable Long serviceOrderId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/service-orders/id/initial-tests"));
        // lista testes iniciais do atendimento.
        InitialTestRes res = initialTestMapper.mappear(
                initialTestService.encontrarTestesIniciaisPeloServiceOrderId(serviceOrderId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}/initial-tests")
    public ResponseEntity<?> createInitialTest(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody InitialTestReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/service-orders/id/initial-tests"));
        // cria teste inicial (tecn. responsável, período, resultado).
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        initialTestService.cadastrar(initialTestMapper.mappear(serviceOrder, req));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/initial-tests")
    public ResponseEntity<InitialTestRes> getInitialTest(@RequestHeader Long userId, @PathVariable Long serviceOrderId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/service-orders/id/initial-tests/id"));
        // detalhe do teste.
        InitialTestRes res = initialTestMapper.mappear(initialTestService.encontrarTestesIniciaisPeloServiceOrderId(serviceOrderId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/initial-tests/{testId}")
    public ResponseEntity<?> updateInitialTest(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @PathVariable Long testId, @RequestBody InitialTestReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/service-orders/id/initial-tests/id"));
        // atualiza teste.
        initialTestService.atualizarTeste(serviceOrderId, testId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/initial-tests/{testId}")
    public ResponseEntity<?> deleteInitialTest(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @PathVariable Long testId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/service-orders/id/initial-tests/id"));
        // remove teste.
        initialTestService.exclusaoLogica(serviceOrderId, testId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
        quoteService.adicionarItems(quoteItem, quote);
        serviceOrderService.adicionarQuote(quote, serviceOrder);
        SoStatus soStatus = soStatusService.encontrarPeloNome(ESoStatus.VALIDAR_ORACAMENTO);
        serviceOrderService.atualizarStatusHistory(serviceOrder, soStatusHistoryMapper.mappear(serviceOrder, soStatus, user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/work-orders")
    public ResponseEntity<List<WorkOrderRes>> listWorkOrders(@RequestHeader Long userId, @PathVariable Long serviceOrderId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/service-orders/id/work-orders"));
        List<WorkOrderRes> res = workOrderMapper.mappear(serviceOrderService.encontrarWorkOrders(serviceOrderId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}/work-orders")
    public ResponseEntity<?> createWorkOrder(@RequestHeader Long userId, @PathVariable Long serviceOrderId, @RequestBody WorkOrderReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/service-orders/id/work-orders"));
        //TODO: cria ordem de trabalho (início do reparo).
        ServiceOrder serviceOrder = serviceOrderService.encontrarPeloId(serviceOrderId);
        WorkOrder workOrder = workOrderService.cadastrar(workOrderMapper.mappear(serviceOrder, req));
        WorkLog workLog = workLogService.cadastrar(workLogMapper.mappear(workOrder, req.worklog()));
        serviceOrderService.adicionarWorkOrder(serviceOrder, workOrderService.adicionarWorkLog(workOrder,workLog));
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
//
//    @PostMapping("/{id}/shipments")
//    public ResponseEntity<?> createShipment(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/service-orders/id/shipments"));
//        //TODO: cria envio, grava código de rastreio e dispara notificação ao cliente.
//
//        return null;
//    }

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
