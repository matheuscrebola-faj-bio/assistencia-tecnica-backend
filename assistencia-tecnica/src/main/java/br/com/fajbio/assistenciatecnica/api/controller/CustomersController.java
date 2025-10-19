package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @GetMapping
//    public ResponseEntity<?> listCustomers(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/customers"));
//        //TODO: lista clientes com busca e paginação.
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createCustomer(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/customers"));
//        //TODO: cria cliente, contatos e endereços (transação).
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCustomerById(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/customers/id"));
//        //TODO: detalhe do cliente.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateCustomer(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/customers/id"));
//        //TODO: atualiza dados do cliente.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteCustomer(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/customers/id"));
//        //TODO: inativa/Remove cliente.
//        return null;
//    }
//
//    @GetMapping("/{customerId}/contacts")
//    public ResponseEntity<?> listCustomerContacts(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/customers/id/contacts"));
//        //TODO: lista contatos do cliente.
//        return null;
//    }
//
//    @PostMapping("/{customerId}/contacts")
//    public ResponseEntity<?> createCustomerContact(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/customers/id/contacts"));
//        //TODO: adiciona contato.
//        return null;
//    }
//
//    @GetMapping("/{customerId}/addresses")
//    public ResponseEntity<?> listCustomerAddresses(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/customers/id/addresses"));
//        //TODO: lista endereços do cliente.
//        return null;
//    }
//
//    @PostMapping("/{customerId}/addresses")
//    public ResponseEntity<?> createCustomerAddress(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/customers/id/addresses"));
//        //TODO: adiciona endereço (e tipo).
//        return null;
//    }
//
//    @PutMapping("{customerId}/contacts/{contactId}")
//    public ResponseEntity<?> updateCustomerContact(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/customers/id/contacts/id"));
//        //TODO: atualiza contato.
//        return null;
//    }
//
//    @PutMapping("/{customerId}/addresses/{addressId}")
//    public ResponseEntity<?> updateCustomerAddress(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/customers/id/address/id"));
//        //TODO: atualiza endereço.
//        return null;
//    }
//
//    @DeleteMapping("/{customerId}/contacts/{contactId}")
//    public ResponseEntity<?> deleteCustomerContact(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/customers/id/contacts/id"));
//        //TODO: remove contato.
//        return null;
//    }
//
//    @DeleteMapping("/{customerId}/addresses/{addressId}")
//    public ResponseEntity<?> deleteCustomerAddress(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/customers/id/address/id"));
//        //TODO: remove endereço.
//        return null;
//    }
}
