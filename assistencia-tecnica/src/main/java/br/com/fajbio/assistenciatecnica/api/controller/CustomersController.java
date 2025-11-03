package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.*;
import br.com.fajbio.assistenciatecnica.api.mapper.*;
import br.com.fajbio.assistenciatecnica.domain.model.Address;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import br.com.fajbio.assistenciatecnica.domain.service.*;
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
    private final AddressMapper addressMapper;
    private final AddressService addressService;
    private final CustomerAddressMapper customerAddressMapper;
    private final CustomerAddressService customerAddressService;

    @GetMapping
    public ResponseEntity<List<CustomerRes>> listCustomers(
//            @RequestHeader Long id
    ){
        //accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/customers"));
        // lista clientes com busca e paginação.
        List<CustomerRes> res = customerMapper.mappear(customerService.encontrarTodos());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(
            //@RequestHeader Long userId,
            @RequestBody CustomerReq req){
        //accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/customers"));
        // cria cliente, contatos e endereços (transação).
        var customer = customerService.cadastrar(customerMapper.mappear(req));
        if (req.contact() != null && req.address() != null){
            var address = addressService.cadastrar(addressMapper.mappear(req.address().address()));
            var cAddress = customerAddressService.cadastrar(customerAddressMapper.mappear(req.address().tipo(), customer, address));
            var contact = customerContactService.cadastrar(customerContactMapper.mappear(req.contact()));
            customerService.atualizar(cAddress, contact, customer.getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerRes> getCustomerById(
            //@RequestHeader Long userId,
            @PathVariable Long customerId){
        //accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/customers/id"));
        // detalhe do cliente.
        var customer = customerService.encontrarPeloId(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CustomerRes res = customerMapper.mappear(customer);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(
            @RequestHeader Long userId,
            @PathVariable Long customerId,
            @RequestBody CustomerReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/customers/id"));
        // atualiza dados do cliente.
        customerService.atualizar(customerId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(
            @RequestHeader Long userId,
            @PathVariable Long customerId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/customers/id"));
        // inativa/Remove cliente.
        customerService.delecaoLogica(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{customerId}/contacts")
    public ResponseEntity<List<CustomerContactRes>> listCustomerContacts(
//            @RequestHeader Long userId,
            @PathVariable Long customerId){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/customers/id/contacts"));
        // lista contatos do cliente.
        Customer customer = customerService.encontrarPeloId(customerId);
        List<CustomerContactRes> res = customerContactMapper.mappear(customer.getContacts());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{customerId}/contacts")
    public ResponseEntity<?> createCustomerContact(
//            @RequestHeader Long userId,
            @PathVariable Long customerId,
            @RequestBody CustomerContactReq req){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/customers/id/contacts"));
        // adiciona contato.
        Customer customer = customerService.encontrarPeloId(customerId);
        CustomerContact customerContact = customerContactService.cadastrar(customerContactMapper.mappear(req, customer));
        customerService.atualizar(customer, customerContact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/addresses")
    public ResponseEntity<List<CustomerAddressRes>> listCustomerAddresses(
//            @RequestHeader Long userId,
            @PathVariable Long customerId){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/customers/id/addresses"));
        // lista endereços do cliente.
        Customer customer = customerService.encontrarPeloId(customerId);
        List<CustomerAddressRes> res = customerAddressMapper.mappear(customer.getAddresses());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<?> createCustomerAddress(
//            @RequestHeader Long userId,
            @PathVariable Long customerId,
            @RequestBody CustomerAddressReq req){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/customers/id/addresses"));
        Customer customer = customerService.encontrarPeloId(customerId);
        Address address = addressService.cadastrar(addressMapper.mappear(req.address()));
        CustomerAddress customerAddress = customerAddressService.cadastrar(customerAddressMapper.mappear(req.tipo(), customer, address));
        customerService.atualizar(customer, customerAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{customerId}/contacts/{contactId}")
    public ResponseEntity<?> updateCustomerContact(
//            @RequestHeader Long userId,
            @PathVariable Long customerId,
            @PathVariable Long contactId,
            @RequestBody CustomerContactReq req) {
//        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/customers/id/contacts/id"));
        // atualiza o contato dentro da lista
        customerService.atualizar(customerId, contactId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<?> updateCustomerAddress(
//            @RequestHeader Long userId,
            @PathVariable Long customerId,
            @PathVariable Long addressId,
            @RequestBody CustomerAddressReq req){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/customers/id/address/id"));
        // atualiza endereço.
        customerService.atualizar(customerId, addressId, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}/contacts/{contactId}")
    public ResponseEntity<?> deleteCustomerContact(
//            @RequestHeader Long userId,
            @PathVariable Long customerId,
            @PathVariable Long contactId) {

//        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/customers/id/contacts/id"));
        customerService.removerContato(customerId, contactId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<?> deleteCustomerAddress(
//            @RequestHeader Long userId,
            @PathVariable Long customerId,
            @PathVariable Long addressId){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/customers/id/address/id"));
        customerService.removerEndereco(customerId, addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
