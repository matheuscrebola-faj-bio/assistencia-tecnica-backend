package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
//
//    @GetMapping
//    public ResponseEntity<?> listUsers(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/users"));
//        //TODO: lista usuários do banco com filtros/paginação.
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/users"));
//        //TODO: cria usuário no banco e define perfis (roles) iniciais.
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/users/id"));
//        //TODO: busca dados completos do usuário.
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/users/id"));
//        //TODO: atualiza dados do usuário.
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/users/id"));
//        //TODO: desativa/Remove usuário (soft/hard, conforme regra).
//        return null;
//    }
//
//    @PostMapping("/{id}/roles/{roleId}")
//    public ResponseEntity<?> addRoleToUser(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/users/id/roles/ID"));
//        //TODO: vincula perfil ao usuário.
//        return null;
//    }
//
//    @GetMapping("/roles")
//    public ResponseEntity<?> listRoles(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/users/id/roles/"));
//        //TODO: lista catálogo de perfis.
//        return null;
//    }
//
//    @DeleteMapping("/{id}/roles/{roleId}")
//    public ResponseEntity<?> removeRoleFromUser(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/users/id/roles/id"));
//        //TODO: remove perfil do usuário.
//        return null;
//    }
}
