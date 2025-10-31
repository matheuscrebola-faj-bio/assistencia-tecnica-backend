package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.RoleRes;
import br.com.fajbio.assistenciatecnica.api.dto.UserReq;
import br.com.fajbio.assistenciatecnica.api.dto.UserRes;
import br.com.fajbio.assistenciatecnica.api.dto.UserUpdate;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.RoleMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.UserMapper;
import br.com.fajbio.assistenciatecnica.domain.model.Role;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.RoleService;
import br.com.fajbio.assistenciatecnica.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final RoleMapper roleMapper;
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<UserRes>> listUsers(@RequestHeader Long userId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/users"));
        // lista usuários do banco com filtros/paginação.
        List<UserRes> res = userService.encontrarTodos();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestHeader Long userId, @RequestBody UserReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/users"));
        // cria usuário no banco e define perfis (roles) iniciais.
        Set<Role> roles = roleService.encontrarPeloNome(req.roles());
        userService.cadastrar(userMapper.mappear(req, roles));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRes> getUserById(@RequestHeader Long reqId, @PathVariable Long userId){
        accessLogService.registrar(accessLogMapper.mappear(reqId, "GET", "/users/id"));
        UserRes res = userMapper.mappear(userService.encontrarPeloId(userId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestHeader Long reqId, @PathVariable Long userId, @RequestBody UserUpdate update){
        accessLogService.registrar(accessLogMapper.mappear(reqId, "PUT", "/users/id"));
        userService.atualizar(userId, update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestHeader Long reqId, @PathVariable Long userId){
        accessLogService.registrar(accessLogMapper.mappear(reqId, "DELETE", "/users/id"));
        // desativa/Remove usuário (soft/hard, conforme regra).
        userService.delecaoLogica(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/roles/{roleId}")
    public ResponseEntity<?> addRoleToUser(@RequestHeader Long reqId, @PathVariable Long userId, @PathVariable Long roleId){
        accessLogService.registrar(accessLogMapper.mappear(reqId, "POST", "/users/id/roles/ID"));
        // vincula perfil ao usuário.
        Role role = roleService.encontrarPeloId(roleId);
        userService.adicionarRoleAoUsuario(role, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleRes>> listRoles(@RequestHeader Long id){
        accessLogService.registrar(accessLogMapper.mappear(id, "GET", "/users/id/roles/"));
        // lista catálogo de perfis.
        List<Role> roles = roleService.encontrarTodos();
        List<RoleRes> res = roleMapper.mappear(roles);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/roles/{roleId}")
    public ResponseEntity<?> removeRoleFromUser(@RequestHeader Long reqId, @PathVariable Long userId, @PathVariable Long roleId){
        accessLogService.registrar(accessLogMapper.mappear(reqId, "DELETE", "/users/id/roles/id"));
        // remove perfil do usuário.
        userService.removerRoleDoUsuario(userId, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
