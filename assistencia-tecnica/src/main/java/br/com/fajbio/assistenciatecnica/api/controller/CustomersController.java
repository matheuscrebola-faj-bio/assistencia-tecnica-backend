package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.*;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.CustomerContactMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.CustomerMapper;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.CustomerContactService;
import br.com.fajbio.assistenciatecnica.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;
    private final CustomerContactMapper customerContactMapper;
    private final CustomerContactService customerContactService;

    @GetMapping
    public ResponseEntity<List<CustomerRes>> listCustomers(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/customers"));
        // lista clientes com busca e paginação.
        List<CustomerRes> res = customerMapper.mappear(customerService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestHeader Long userId, @RequestBody CustomerReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/customers"));
        // cria cliente, contatos e endereços (transação).
        customerService.cadastrar(customerMapper.mappear(req));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerRes> getCustomerById(@RequestHeader Long userId, @PathVariable Long customerId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/customers/id"));
        // detalhe do cliente.
        CustomerRes res = customerMapper.mappear(customerService.encontrarPeloId(customerId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestHeader Long userId, @PathVariable Long customerId, @RequestBody CustomerUpdate update){
        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/customers/id"));
        // atualiza dados do cliente.
        Customer customer = customerService.encontrarPeloId(customerId);
        customerService.atualizar(customerMapper.mappear(customer, update));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader Long userId, @PathVariable Long customerId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/customers/id"));
        // inativa/Remove cliente.
        Customer customer = customerService.encontrarPeloId(customerId);
        customerService.delecaoLogica(customerMapper.mappear(customer,false));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{customerId}/contacts")
    public ResponseEntity<List<CustomerContactRes>> listCustomerContacts(@RequestHeader Long userId, @PathVariable Long customerId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/customers/id/contacts"));
        // lista contatos do cliente.
        Customer customer = customerService.encontrarPeloId(customerId);
        List<CustomerContactRes> res = customerContactMapper.mappear(customer.getContacts());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{customerId}/contacts")
    public ResponseEntity<?> createCustomerContact(@RequestHeader Long userId, @PathVariable Long customerId, @RequestBody CustomerContactReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/customers/id/contacts"));
        //TODO: adiciona contato.
        Customer customer = customerService.encontrarPeloId(customerId);
        CustomerContact customerContact = customerContactService.cadastrar(customerContactMapper.mappear(req));
        customerService.atualizar(customerMapper.mappear(customer, customerContact));
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
